package com.example.idledrink.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.*
import com.example.idledrink.database.entity.BuildingEntity
import com.example.idledrink.model.buildings.ABuilding
import com.example.idledrink.model.buildings.AStockage
import com.example.idledrink.model.buildings.generators.AlchoolInhiniter
import com.example.idledrink.model.buildings.generators.Distillery
import com.example.idledrink.model.buildings.generators.FactorySV
import com.example.idledrink.ui.IdleDrinkDialog
import com.example.idledrink.ui.build.BuildingObserver

class BuildAdapter(val context: Context, val listener: BuildingObserver): RecyclerView.Adapter<BuildAdapter.ViewHolder>() {

    var builds = ArrayList<ABuilding>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_build_row, parent, false))
    }

    override fun getItemCount(): Int {
        return builds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(builds[position])
    }

    inner class ViewHolder (val view: View) : ABaseAdapter<ABuilding>.ABaseViewHolder(view) {

        private val icon: ImageView = view.findViewById(R.id.rv_build_icon)
        private val text: TextView = view.findViewById(R.id.rv_build_name)
        private val tvProductionOrStockLimit: TextView = view.findViewById(R.id.rv_build_production)
        private val tvDescription: TextView = view.findViewById(R.id.rv_build_description)
        private val level: TextView = view.findViewById(R.id.rv_build_level)
        private val upgradeButton: Button = view.findViewById(R.id.rv_build_upgrade)
        private val addEmployee: Button = view.findViewById(R.id.rv_build_add_employee)
        private val employeeCount: TextView = view.findViewById(R.id.rv_build_employee)
        lateinit var tvProductionTag: TextView

        override fun bindView(item: ABuilding) {
            bindIcon(item)
            bindTextfields(item)
            bindButtons(item)
        }

        private fun bindButtons(item: ABuilding) {
            addEmployee.text = "Add Employee (${item.employeeCost.toInt().toString()} SV)"
            addEmployee.setOnClickListener {
                when (item.canAddEmployee()) {
                    1 -> {
                        IdleDrinkDialog(context, getIconForItem(item), context.getString(R.string.dialog_build_cant_buy_employees),
                            item.displayName).show()
                    }
                    2 -> {
                        IdleDrinkDialog(context, getIconForItem(item), context.getString(R.string.dialog_build_cant_add_more_employees),
                            item.displayName).show()
                    }
                    else -> {
                        listener.onItemUpdated(item, modification = BuildingEntity.UPADTE.ADD_EMPLOYEE)
                        item.addEmployee(1)
                        item.playerStats.SV -= item.employeeCost
                        notifyDataSetChanged()
                    }
                }
            }
            upgradeButton.text = "Upgrade (${item.upgradeCost} SV)"
            upgradeButton.setOnClickListener {
                if (item.playerStats.SV >= item.upgradeCost) {
                    item.playerStats.SV -= item.upgradeCost
                    item.onUpgrade()
                    listener.onItemUpdated(item, modification = BuildingEntity.UPADTE.UPGRADE_BUILDING)
                    notifyDataSetChanged()
                } else {
                    IdleDrinkDialog(context, getIconForItem(item), context.getString(R.string.dialog_build_cant_upgrade_building), item.displayName).show()
                }
            }
        }

        private fun bindTextfields(item: ABuilding) {
            text.text = item.displayName
            tvDescription.text = item.description
            tvProductionOrStockLimit.text = when(item){
                is FactorySV -> {
                    val splited = item.computeAngGetProduction().toString().split(".")
                    "Production : " + splited[0] + "," + splited[1].substring(0, 1)
                }
                is Distillery -> {
                    val splited = item.computeAngGetProduction().toString().split(".")
                    "Production : " + splited[0] + "," + splited[1].substring(0, 1)
                }
                is AlchoolInhiniter -> {
                    "Reduction " + Utils.getCuttedString(item.computeAngGetProduction(), 2)
                }
                else -> "Stockage max : ${(item as AStockage).stockLimit.toString()}"
            }

            level.text = "Level : " + item.level.toString()
            employeeCount.text = "Employee : ${item.getEmployeeCount().toString()} / ${item.getMaxEmployee()}"
        }

        private fun getIconForItem(item: ABuilding): Drawable? {
            return when(item) {
                is Distillery -> context.resources.getDrawable(R.drawable.pink_potion)
                is AlchoolInhiniter -> context.resources.getDrawable(R.drawable.distiller)
                is FactorySV -> context.resources.getDrawable(R.drawable.sousverres)
                else -> context.resources.getDrawable(R.drawable.logo)
            }
        }

        private fun bindIcon(item: ABuilding) {
            val iconDrawable = getIconForItem(item)
            if (iconDrawable != null)
                icon.setImageDrawable(iconDrawable)
        }
    }
}
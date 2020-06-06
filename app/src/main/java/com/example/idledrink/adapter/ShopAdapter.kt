package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.model.items.AItem

class ShopAdapter(val context: Context) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    var shopItems: ArrayList<AItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_shop_row, parent, false))
    }

    override fun getItemCount(): Int {
        return this.shopItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(shopItems[position])
    }

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<AItem>(view, R.menu.item_onclick_menu) {

        private val tvItemName: TextView = view.findViewById(R.id.rv_shop_tv)
        private val ivIcon: ImageView = view.findViewById(R.id.rv_shop_icon)

        override fun bindView(item: AItem) {
            tvItemName.text = item.displayName
        }

        override fun onItemMenuClick(itemId: Int) {
            when(itemId) {
                R.id.buy->{
                    Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
                }
                R.id.use->{
                    Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun unBindView() {
            tvItemName.text = null
        }
    }
}

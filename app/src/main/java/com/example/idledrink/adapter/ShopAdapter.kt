package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.idledrink.R
import com.example.idledrink.model.items.AItem

class ShopAdapter(val context: Context) : ABaseAdapter<AItem, ShopAdapter.ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.rv_shop_row
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(getLayout(), parent, false))
    }

    inner class ViewHolder (view: View) : ABaseViewHolderWithPopupMenu<AItem>(view) {

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

        override fun getMenuLayout(): Int {
            return R.menu.item_onclick_menu
        }
    }
}

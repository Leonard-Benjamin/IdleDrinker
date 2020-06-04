package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.idledrink.R
import com.example.idledrink.model.items.AItem

class ShopAdapter(val context: Context) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    var shopItems: ArrayList<AItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_shop_row, parent, false))
    }

    override fun getItemCount(): Int {
        return this.shopItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shopItems[position])
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: AItem) {

        }
    }
}

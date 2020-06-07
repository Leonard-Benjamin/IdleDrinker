package com.example.idledrink.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ABaseAdapter<T, VH : ABaseViewHolder<T>>: RecyclerView.Adapter<ABaseViewHolder<T>>() {

    var data : ArrayList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: ABaseViewHolder<T>, position: Int) {
        holder.bindView(this.data[position])
    }

    abstract fun getLayout(): Int
}
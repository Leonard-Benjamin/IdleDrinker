package com.example.idledrink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView

abstract class ABaseAdapter<T>(val context: Context,
                               protected val withPopUpMenu: Boolean,
                               val adapterListener: ABaseAdapterListener,
                               private var datas: List<T> = emptyList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(context)
            .inflate(viewType, parent, false)
            , viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(datas[position])
    }

    fun setData(data: List<T>) {
        this.datas = datas
        this.notifyDataSetChanged()
    }

    abstract fun getLayoutId(position: Int)

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun getMenuLayout(): Int

    fun getMenuLayoutIfNeeded(): Int {
        if (withPopUpMenu) {
            getMenuLayout()
        }
        return 0
    }

    abstract inner class Binder<T> {
        abstract fun bind(item: T)
        fun binMenuLayoutIfNeeded() {
            if (withPopUpMenu) {

            }
        }
    }


}
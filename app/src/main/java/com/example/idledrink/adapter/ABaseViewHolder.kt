package com.example.idledrink.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ABaseViewHolder<T> (view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindView(item: T)

}


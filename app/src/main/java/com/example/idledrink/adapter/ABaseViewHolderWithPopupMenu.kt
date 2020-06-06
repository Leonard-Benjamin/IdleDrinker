package com.example.idledrink.adapter

import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

abstract class ABaseViewHolderWithPopupMenu<T> (val view: View, menuLayout: Int) : ABaseViewHolder<T>(view) {
    init {
        view.setOnClickListener {
            val pop= PopupMenu(view.context,it)
            pop.inflate(menuLayout)
            pop.setOnMenuItemClickListener {item->
                this.onItemMenuClick(item.itemId)
                true
            }
            pop.show()
        }
    }

    open fun onItemMenuClick(itemId: Int) {
        Toast.makeText(view.context, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }

    abstract fun unBindView()
}
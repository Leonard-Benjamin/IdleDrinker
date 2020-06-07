package com.example.idledrink.adapter

import android.view.View
import android.widget.PopupMenu

abstract class ABaseViewHolderWithPopupMenu<T> (val view: View, menuLayout: Int) : ABaseViewHolder<T>(view) {

    lateinit var popupMenu: PopupMenu

    init {
        view.setOnClickListener {
            this.popupMenu = PopupMenu(view.context,it)
            this.popupMenu.inflate(menuLayout)
            this.popupMenu.setOnMenuItemClickListener {item->
                this.onItemMenuClick(item.itemId)
                true
            }
            this.popupMenu.show()
        }
    }

    open fun onItemMenuClick(itemId: Int) {
        throw IllegalAccessException("You must override onItemMenuClick in your child ViewHolder")
    }

    abstract fun unBindView()
}
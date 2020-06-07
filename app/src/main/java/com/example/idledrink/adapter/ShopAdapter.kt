package com.example.idledrink.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.idledrink.R
import com.example.idledrink.model.items.AItem

class ShopAdapter(context: Context, data: ArrayList<AItem>, listener: ABaseAdapterListener, withPopoupMenu: Boolean)
    : ABaseAdapter<AItem>(data, context, withPopoupMenu, listener) {

}

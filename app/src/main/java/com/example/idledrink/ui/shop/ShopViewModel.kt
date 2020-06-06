package com.example.idledrink.ui.shop

import android.app.Application
import com.example.idledrink.model.items.AItem
import com.example.idledrink.model.items.usable.ShieldItem
import com.example.idledrink.ui.BaseViewModel

class ShopViewModel(application: Application) : BaseViewModel<AItem>(application) {

    override fun populateList() {
        val list = ArrayList<AItem>()
        list.add(ShieldItem())
        list.add(ShieldItem())
        list.add(ShieldItem())
        list.add(ShieldItem())
        list.add(ShieldItem())
        this.dataList = list
        //this.dataList = mFireBaseProvider.getItemsForUser(user)
    }
}



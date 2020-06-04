package com.example.idledrink.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.model.items.AItem

class ShopViewModel : ViewModel() {

    private val testlist: MutableLiveData<ArrayList<AItem>> = MutableLiveData()
    init {
        testlist.value = arrayListOf(
        )
    }

    val text: LiveData<ArrayList<AItem>> = testlist
}
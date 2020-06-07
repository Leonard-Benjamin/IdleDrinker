package com.example.idledrink.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.User


class MainActivityViewModel(val user: User) : ViewModel() {
    var mutableLiveData: MutableLiveData<Int> = MutableLiveData()
    var newMessageCount: Int = 0
    var mFireBaseProvider : FireBaseManager = FireBaseManager.instance

    init {
         mFireBaseProvider.hasNewMessages(user) {populateList(it) }

        mutableLiveData.value = newMessageCount
    }

    private fun populateList(count: Int) {
        newMessageCount = count
    }
}



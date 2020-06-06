package com.example.idledrink.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Message
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

    fun sendMessage(message: String, username: String = "", userId: String = "") {
        this.mFireBaseProvider.sendMessage(message, username, userId)
    }

    fun updateMessage(message: Message) {
        FireBaseManager.instance.updateMessage(message)
    }
}



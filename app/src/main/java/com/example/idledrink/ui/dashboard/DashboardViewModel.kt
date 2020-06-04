package com.example.idledrink.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Message

interface DashBoardListener {
    fun onNewEntry(list: ArrayList<Message>)
}

class DashboardViewModel : ViewModel(), DashBoardListener {
    var mutableLiveData: MutableLiveData<ArrayList<Message>> = MutableLiveData()
    var entriesArrayList: ArrayList<Message> = ArrayList()
    var mFireBaseProvider : FireBaseManager = FireBaseManager.instance

    init {
        populateList()
        mutableLiveData.value = entriesArrayList
    }

    private fun populateList() {
        entriesArrayList = mFireBaseProvider.getMessages(this)
    }

    override fun onNewEntry(list: ArrayList<Message>) {
        if (list.size > 0) {
            list.sortWith(compareBy { it.insertedTime })
            if (list.size > 25) {
                val newList = list.drop(list.size - 25) as ArrayList<Message>
                this.mutableLiveData.value = newList
            } else {
                this.mutableLiveData.value = list
            }
        }
    }

    fun sendMessage(message: String, username: String = "", userId: String = "") {
        this.mFireBaseProvider.sendMessage(message, username, userId)
    }

    fun updateMessage(message: Message) {
        FireBaseManager.instance.updateMessage(message)
    }
}



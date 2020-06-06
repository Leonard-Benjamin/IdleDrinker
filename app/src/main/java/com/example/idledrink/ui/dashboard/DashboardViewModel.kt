package com.example.idledrink.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Message
import com.example.idledrink.ui.BaseViewModel

interface DashBoardListener {
    fun onNewEntry(list: ArrayList<Message>)
}

class DashboardViewModel(application: Application) : BaseViewModel<Message>(application), DashBoardListener {

    override fun populateList() {
        dataList = mFireBaseProvider.getMessages(this)
    }

    override fun onNewEntry(list: ArrayList<Message>) {
        if (list.size > 0) {
            list.sortWith(compareBy { it.insertedTime })
            if (list.size > 25) {
                val newList = list.drop(list.size - 25) as ArrayList<Message>
                this.mutableList.value = newList
            } else {
                this.mutableList.value = list
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



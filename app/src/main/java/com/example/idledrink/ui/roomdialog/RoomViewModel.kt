package com.example.idledrink.ui.roomdialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.Room

interface RoomListener {
    fun onNewEntry(list: ArrayList<Room>)
}

class RoomViewModel: ViewModel(), RoomListener {
    var mutableLiveData: MutableLiveData<ArrayList<Room>> = MutableLiveData()
    var roomArrayList: ArrayList<Room> = ArrayList<Room>()
    var mFireBaseProvider : FireBaseManager

    init {
        mFireBaseProvider = FireBaseManager.instance
        populateList()
        mutableLiveData.value = roomArrayList
    }

    private fun populateList() {
        roomArrayList = mFireBaseProvider.getRooms(this)
    }

    override fun onNewEntry(list: ArrayList<Room>) {
        this.mutableLiveData.value = list
    }

    fun updateRoom(room: Room) {
        mFireBaseProvider.updateRoom(room)

    }
}
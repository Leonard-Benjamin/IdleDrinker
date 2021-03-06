package com.example.idledrink.ui.roomdialog

import android.app.Application
import com.example.idledrink.database.firebase.Room
import com.example.idledrink.ui.BaseViewModel

interface RoomListener {
    fun onNewEntry(list: ArrayList<Room>)
}

class RoomViewModel(application: Application): BaseViewModel<Room>(application), RoomListener {

    override fun populateList() {
        dataList = mFireBaseProvider.getRooms(this)
    }

    override fun onNewEntry(list: ArrayList<Room>) {
        this.mutableList.value = list
    }

    fun updateRoom(room: Room) {
        mFireBaseProvider.updateRoom(room)
    }

    fun deleteRoom(room: Room) {
        mFireBaseProvider.deleteRoom(room)
    }
}
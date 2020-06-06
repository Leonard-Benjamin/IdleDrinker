package com.example.idledrink.database.firebase

import android.renderscript.Sampler
import com.example.idledrink.Utils
import com.example.idledrink.model.items.AItem
import com.example.idledrink.ui.dashboard.DashBoardListener
import com.example.idledrink.ui.roomdialog.RoomListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class FireBaseManager {

    val mDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    private object HOLDER {
        val INSTANCE = FireBaseManager()
    }

    companion object {
        val instance: FireBaseManager by lazy { HOLDER.INSTANCE }
    }

    fun writeNewUser(
        userId: String,
        name: String
    ) {
        val user = User(userId, name)
        mDatabase.getReference("User").child(userId).setValue(user)
    }

    fun writeRoom(
        roomId: String,
        name: String,
        users: ArrayList<User>) {
        val room = Room(roomId, name, users)
        mDatabase.getReference("Room").child(roomId).setValue(room)
    }

    fun getRooms(listener: RoomListener): ArrayList<Room> {
        var rooms = ArrayList<Room>()
        mDatabase.getReference("Room").addValueEventListener(object :ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: ArrayList<Room> = ArrayList()
                for (ds in dataSnapshot.children) {
                    val roomModel: Room = ds.getValue(Room::class.java)!!
                    list.add(roomModel)
                }
                listener.onNewEntry(list)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("loadRoom:onCancelled ${databaseError.toException()}")
            }
        })
        return rooms
    }

    fun updateRoom(room: Room) {
        val child = mDatabase.getReference("Room").child(room.roomId).child("users")
        child.setValue(room.users)
    }

    fun hasNewMessages(user: User,hasNew: (Int) -> (Unit)) {
        mDatabase.getReference("Messages").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var numberOfNewMessage = 0
                for (ds in dataSnapshot.children) {
                    val message: Message = ds.getValue(Message::class.java)!!
                    if (!message.readBy.contains(user)) {
                        numberOfNewMessage += 1
                    }
                }
                hasNew(numberOfNewMessage)
            }
        })
    }

    fun getMessages(listener: DashBoardListener): ArrayList<Message> {
        var messages = ArrayList<Message>()
        mDatabase.getReference("Messages").addValueEventListener(object :ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<Message>()
                for (ds in dataSnapshot.children) {
                    val message: Message = ds.getValue(Message::class.java)!!
                    list.add(message)
                }
                listener.onNewEntry(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadMessages:onCancelled ${databaseError.toException()}")
            }
        })
        return messages
    }

    fun sendMessage(text: String, username: String, userId: String) {
        val timeStamp = Date().time
        val user = User(userId, username)
        val message: Message = Message(UUID.randomUUID().toString(), text, timeStamp, arrayListOf(user), user)
        mDatabase.getReference("Messages").child(message.messageId).setValue(message)
    }

    fun updateMessage(message: Message) {
        val child = mDatabase.getReference("Messages").child(message.messageId).child("readBy")
        child.setValue(message.readBy)
    }

    fun getItemsForUser(user: User): ArrayList<AItem> {
        return ArrayList()
    }
}
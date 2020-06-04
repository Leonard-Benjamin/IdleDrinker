package com.example.idledrink.database.firebase

import kotlin.collections.ArrayList

class Message(val messageId: String = "",
                   val text: String = "",
                   var insertedTime: Long? = null,
                   var readBy: ArrayList<User> = ArrayList(),
              var sender: User = User()
) {
    fun addUserRead(user: User) {
        this.readBy.add(user)
    }
}
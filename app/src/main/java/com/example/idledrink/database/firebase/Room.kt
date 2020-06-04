package com.example.idledrink.database.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Room(val roomId: String = "",
                val name: String = "",
                var users: ArrayList<User> = ArrayList()) {
}
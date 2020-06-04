package com.example.idledrink.database.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val uuid: String = "",
                val name: String = "") {
}
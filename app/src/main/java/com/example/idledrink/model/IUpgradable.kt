package com.example.idledrink.model

interface IUpgradable {
    var level: Int
    fun onUpgrade()
}
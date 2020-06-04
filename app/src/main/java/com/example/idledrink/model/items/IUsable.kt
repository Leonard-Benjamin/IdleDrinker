package com.example.idledrink.model.items

abstract class IUsable: AItem(), IITemComportement {
    abstract fun onUse()

    override fun doAction() {
        onUse()
    }
}
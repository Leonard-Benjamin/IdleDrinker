package com.example.idledrink.model.items

abstract class IAppliable: AItem(), IITemComportement {
    fun onApply() {

    }

    override fun doAction() {
        onApply()
    }
}
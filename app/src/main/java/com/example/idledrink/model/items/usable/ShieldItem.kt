package com.example.idledrink.model.items.usable

import com.example.idledrink.model.items.IUsable

class ShieldItem: IUsable() {
    override fun onUse() {
        print("used")
    }

    override var displayName: String = "Shield"
    override var description: String = "Pour se protéger des attaques"
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val tagName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val dataBaseColumnName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}
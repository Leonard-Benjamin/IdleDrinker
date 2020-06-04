package com.example.idledrink.model.items.usable

import com.example.idledrink.model.items.IUsable

class ShieldItem: IUsable() {
    override fun onUse() {
        print("used")
    }

    override var displayName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var description: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override val tagName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val dataBaseColumnName: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}
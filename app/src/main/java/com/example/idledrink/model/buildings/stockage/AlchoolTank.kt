package com.example.idledrink.model.buildings.stockage

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AStockage

class AlchoolTank(level: Int, employee: Int): AStockage(level, employee) {
    override var stockLimit: Int = 10

    override var tagName: String = "AlchoolTank"
    override val displayName: String = "Alchool Tank"
    override val description: String = "Pour stocker d'énormes quantités d'alchool"
    override var upgradeCost: Int = 6
    override val icon: Drawable? = null
    override val dataBaseColumnName: String = "alchooltank"
}
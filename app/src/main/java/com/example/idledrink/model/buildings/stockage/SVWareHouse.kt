package com.example.idledrink.model.buildings.stockage

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AStockage

class SVWareHouse(level: Int, employee: Int): AStockage(level, employee) {
    override var stockLimit: Int = 10
    override var tagName: String = "SVWareHouse"
    override val displayName: String = "SV Warehouse"
    override val description: String = "Pour stocker plus de sous-verres"
    override var upgradeCost: Int = 6
    override val icon: Drawable? = null
    override val dataBaseColumnName: String = "svwarehouse"
}
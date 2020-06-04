package com.example.idledrink.model.buildings.generators

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AGenerator

class Distillery(level: Int, employee: Int) : AGenerator(level, employee) {
    override val displayName: String
        get() = "Distilery"

    override val description: String
        get() = "Devenez un Jean-Michel certifié grace à cette Distillerie.\nProduit unique : Carapils"

    override val icon: Drawable? = null

    override var upgradeCost: Int = 6

    override val dataBaseColumnName: String = "distilery"

    override fun computeAndGetUpgradeCost(): Int {
        return (this.level + 1) * 12
    }

    override fun computeAngGetProduction(): Float {
        playerStats.CLPerSecond = this.level * 1f + (this.getEmployeeCount() * 5f)
        return playerStats.CLPerSecond
    }

    override fun applyEffect() {
        playerStats.CLPerSecond += 1f
    }

    override var tagName: String = "Distillery"

    override var productionTag: String = "CL / Sec"
}
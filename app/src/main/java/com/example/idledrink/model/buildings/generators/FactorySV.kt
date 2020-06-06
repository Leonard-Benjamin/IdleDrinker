package com.example.idledrink.model.buildings.generators

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AGenerator

class FactorySV(level: Int, employee: Int) : AGenerator(level, employee) {
    override val displayName: String
        get() = "SV Factory"

    override val description: String
        get() = "Usine de production de Sous-Verres,\nindispensable pour mener des attaques fructueuses et se protéger de ses adversaires"

    override var upgradeCost: Int = 12

    override val icon: Drawable? = null

    override val dataBaseColumnName: String = "svfactory"

    override fun computeAndGetUpgradeCost(): Int {
        return (this.level + 1) * 12
    }

    override fun computeAngGetProduction(): Float {
        playerStats.SVPerSecond = this.level * 100f + (this.getEmployeeCount() * 12f)
        return playerStats.SVPerSecond
    }

    override fun applyEffect() {
        playerStats.SVPerSecond += 100f
    }

    override var tagName: String = "FactorySV"

    override var productionTag: String = "SV / Sec"

}
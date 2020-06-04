package com.example.idledrink.model.buildings.generators

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AGenerator

class AlchoolInhiniter(level: Int, employee: Int) : AGenerator(level, employee) {

    override val displayName: String
        get() = "Alchool Inhibiter"

    override val description: String
        get() = "L'innhibiteur d'alcool est un bon moyen de prendre du répit lorsque vos compagnons de jeu vous saoûlent un peu trop"

    override var upgradeCost: Int = 8

    override val icon: Drawable? = null

    override val dataBaseColumnName: String = "inhibiter"

    override fun computeAndGetUpgradeCost(): Int {
        return (this.level + 1) * 6
    }

    override fun computeAngGetProduction(): Float {
        playerStats.ATRetPerSecond = this.level * 1f
        return playerStats.ATRetPerSecond
    }

    override fun applyEffect() {
        playerStats.ATRetPerSecond += 1f
    }

    override var tagName: String = "AlchoolInhiniter"
    override val productionTag: String = "AT / Sec"
}
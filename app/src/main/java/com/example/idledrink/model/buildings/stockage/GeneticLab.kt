package com.example.idledrink.model.buildings.stockage

import android.graphics.drawable.Drawable
import com.example.idledrink.model.buildings.AStockage

class GeneticLab(level: Int, employee: Int): AStockage(level, employee) {
    override var stockLimit: Int = 10
    override var tagName: String = "GeneticLab"
    override val displayName: String = "Genetic Laboratory"
    override val description: String = "Pour améliorer votre résistance à l'alchool"
    override var upgradeCost: Int = 6
    override val icon: Drawable? = null
    override val dataBaseColumnName: String = "geneticlab"
}
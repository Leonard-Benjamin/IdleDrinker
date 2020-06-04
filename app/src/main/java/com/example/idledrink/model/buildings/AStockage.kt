package com.example.idledrink.model.buildings

import com.example.idledrink.model.IUpgradable

abstract class AStockage(level: Int, employee: Int): ABuilding(), IUpgradable {

    init {
        this.level = level
        this.setEmployeeCount(employee)
    }

    abstract var stockLimit: Int

    override fun onUpgrade() {
        super.onUpgrade()
        this.stockLimit += 10
    }
}
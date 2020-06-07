package com.example.idledrink.model.buildings

abstract class AGenerator(level: Int, employees: Int) : ABuilding() {

    companion object Production {
        fun getProduction(level: Int): Float {
            return level * 1f
        }
    }

    init {
        this.level = level
        this.setEmployeeCount(employees)
        this.computeAngGetProduction()
    }

    abstract val productionTag: String

    override fun onUpgrade() {
        super.onUpgrade()
        this.applyEffect()
        this.upgradeCost = this.computeAndGetUpgradeCost()
    }

    override fun addEmployee(number: Int) {
        super.addEmployee(number)
        computeAngGetProduction()
    }

    abstract fun computeAndGetUpgradeCost() : Int

    abstract fun computeAngGetProduction(): Float

    abstract fun applyEffect()

}
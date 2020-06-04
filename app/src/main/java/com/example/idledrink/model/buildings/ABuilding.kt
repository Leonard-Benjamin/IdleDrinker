package com.example.idledrink.model.buildings

import android.graphics.drawable.Drawable
import com.example.idledrink.PlayerStatsManager
import com.example.idledrink.model.IIdentifiable
import com.example.idledrink.model.IUpgradable

abstract class ABuilding: IUpgradable, IIdentifiable {
    private var employees: Int = 0
    var baseMaxEmployee: Int = 2

    override var level: Int = 0

    abstract var upgradeCost: Int
    abstract val icon: Drawable?

    var employeeCost: Float = 50f

    val playerStats = PlayerStatsManager.instance

    fun getMaxEmployee(): Int {
        return this.baseMaxEmployee + this.level
    }

    fun getEmployeeCount(): Int {
        return this.employees
    }

    fun setEmployeeCount(employee: Int) {
        this.employees = employee
    }

    open fun addEmployee(numberOfEmployeeToAdd: Int) {
        assert(numberOfEmployeeToAdd > 0)
        //TODO modify prroduction based on number of employees and buulding level
        this.employees += numberOfEmployeeToAdd
    }

    fun removeEmployee(numberOfEmployeeToRemove: Int) {
        assert(numberOfEmployeeToRemove > 0)
        this.employees -= numberOfEmployeeToRemove
    }

    fun canAddEmployee(): Int {
        var return_code = 0
        if (this.playerStats.SV < this.employeeCost)
            return_code = 1
        if (this.employees >= this.getMaxEmployee())
            return_code = 2
        return return_code
    }

    override fun onUpgrade() {
        this.level += 1
    }
}

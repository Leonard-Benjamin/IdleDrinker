package com.example.idledrink.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val BUILDING_TABLE_NAME = "buildings_table"

@Entity(tableName = BUILDING_TABLE_NAME)
class BuildingEntity(@PrimaryKey(autoGenerate = false) val id: Long,
                     var distilery: Int = 0,
                     var distilertEmployees: Int = 0,
                     var inhibiter: Int = 0,
                     var inhibiterEmployees: Int = 0,
                     var svfactory: Int = 0,
                     var svfactoryEmployees: Int = 0,
                     var geneticlab: Int = 0,
                     var geneticlabEmployees: Int = 0,
                     var alchooltank: Int = 0,
                     var alchooltankEmployees: Int = 0,
                     var svwarehouse: Int = 0,
                     var svwarehouseEmployees: Int = 0) {
    enum class UPADTE {
        UPGRADE_BUILDING,
        ADD_EMPLOYEE
    }
}
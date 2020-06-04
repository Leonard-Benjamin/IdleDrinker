package com.example.idledrink.ui.build

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.PlayerStatsManager
import com.example.idledrink.database.BuildingDataBase
import com.example.idledrink.database.entity.BuildingEntity
import com.example.idledrink.database.entity.StatEntity
import com.example.idledrink.database.repository.BuildingRepository
import com.example.idledrink.database.repository.StatsRepository
import com.example.idledrink.model.buildings.ABuilding
import com.example.idledrink.model.buildings.generators.AlchoolInhiniter
import com.example.idledrink.model.buildings.generators.Distillery
import com.example.idledrink.model.buildings.generators.FactorySV
import com.example.idledrink.model.buildings.stockage.AlchoolTank
import com.example.idledrink.model.buildings.stockage.GeneticLab
import com.example.idledrink.model.buildings.stockage.SVWareHouse
import org.w3c.dom.Entity

class BuildViewModel(application: Application) : ViewModel() {

    var playerStatsManager: PlayerStatsManager = PlayerStatsManager.instance

    fun updateData(item: ABuilding, modification: BuildingEntity.UPADTE) {
        when(item) {
            is Distillery -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.distilertEmployees += 1
                } else {
                    building.distilery = item.level
                    playerStatsManager.CLPerSecond = item.computeAngGetProduction()
                }
            }
            is AlchoolInhiniter -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.inhibiterEmployees += 1
                } else {
                    building.inhibiter = item.level
                    playerStatsManager.ATRetPerSecond = item.computeAngGetProduction()

                }
            }
            is FactorySV -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.svfactoryEmployees += 1
                } else {
                    building.svfactory = item.level
                    playerStatsManager.SVPerSecond = item.computeAngGetProduction()
                }
            }
            is GeneticLab -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.geneticlabEmployees += 1
                } else {
                    building.distilery = item.level
                }
            }
            is AlchoolTank -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.alchooltankEmployees += 1
                } else {
                    building.alchooltank = item.level
                }
            }
            is SVWareHouse -> {
                if (modification == BuildingEntity.UPADTE.ADD_EMPLOYEE) {
                    building.svwarehouseEmployees += 1
                } else {
                    building.svwarehouse = item.level
                }
            }
            else -> print("ok")
        }
        buildingsRepository.insert(building)
    }

    fun updateStats(at: Float, cl: Float, sv: Float) {
        playerStatsManager.AT = at
        playerStatsManager.CL = cl
        playerStatsManager.SV = sv
    }

    private val buildingsRepository: BuildingRepository

    private var building: BuildingEntity

    private val testlist: MutableLiveData<ArrayList<ABuilding>> = MutableLiveData()
    init {
        val buildingDao = BuildingDataBase.getDatabase(application).buildingDao()

        buildingsRepository = BuildingRepository(buildingDao)
        building = buildingsRepository.getBuildings(1)
        if (building == null) {
            buildingsRepository.insert(BuildingEntity(1))
            building = buildingsRepository.getBuildings(1)
        }

        testlist.value = arrayListOf(
            AlchoolInhiniter(building.inhibiter, building.inhibiterEmployees),
            Distillery(building.distilery, building.distilertEmployees),
            FactorySV(building.svfactory, building.svfactoryEmployees),
            GeneticLab(building.geneticlab, building.geneticlabEmployees),
            AlchoolTank(building.alchooltank, building.alchooltankEmployees),
            SVWareHouse(building.svwarehouse, building.svwarehouseEmployees)
        )
    }

    val text: LiveData<ArrayList<ABuilding>> = testlist
}

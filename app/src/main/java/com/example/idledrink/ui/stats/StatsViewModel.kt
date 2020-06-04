package com.example.idledrink.ui.stats

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idledrink.database.BuildingDataBase
import com.example.idledrink.database.entity.BuildingEntity
import com.example.idledrink.database.repository.BuildingRepository
import com.example.idledrink.model.buildings.ABuilding

class StatsViewModel(application: Application): ViewModel() {
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
    }

    val text: LiveData<ArrayList<ABuilding>> = testlist
}
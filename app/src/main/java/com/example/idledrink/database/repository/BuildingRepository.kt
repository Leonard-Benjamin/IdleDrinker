package com.example.idledrink.database.repository

import androidx.lifecycle.LiveData
import com.example.idledrink.database.dao.BuildingDao
import com.example.idledrink.database.entity.BuildingEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class BuildingRepository(private val buildingDao: BuildingDao) {

    fun getBuildings(id: Long): BuildingEntity = runBlocking {
        GlobalScope.async {
            return@async buildingDao.getBuildingsFromId(id)
        }.await()
    }

    fun getBuildingsAsLiveDate(id: Long): LiveData<BuildingEntity> = runBlocking {
        GlobalScope.async {
            return@async buildingDao.getBuildingsFromIdAsLiveDate(id)
        }.await()
    }

    fun insert(buildings: BuildingEntity) = runBlocking() {
        GlobalScope.async {
            return@async buildingDao.insert(buildings)
        }.await()
    }
}
package com.example.idledrink.database.repository

import androidx.lifecycle.LiveData
import com.example.idledrink.database.dao.StatsDao
import com.example.idledrink.database.entity.StatEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class StatsRepository(private val statsDao: StatsDao) {

    fun getStats(id: Long): StatEntity = runBlocking {
        GlobalScope.async {
            return@async statsDao.getStatsFromId(id)
        }.await()
    }

    fun getStatsAsLiveDate(id: Long): LiveData<StatEntity> = runBlocking {
        GlobalScope.async {
            return@async statsDao.getStatsFromIdAsLiveDate(id)
        }.await()
    }

    fun insert(buildings: StatEntity) = runBlocking() {
        GlobalScope.async {
            return@async statsDao.insert(buildings)
        }.await()
    }
}
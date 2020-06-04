package com.example.idledrink.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.idledrink.database.entity.STAT_TABLE
import com.example.idledrink.database.entity.StatEntity

@Dao
abstract class StatsDao {

    @Query("SELECT * from $STAT_TABLE WHERE id = :id")
    abstract fun getStatsFromId(id: Long): StatEntity

    @Query("SELECT * from $STAT_TABLE WHERE id = :id")
    abstract fun getStatsFromIdAsLiveDate(id: Long): LiveData<StatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(buildings: StatEntity)
}
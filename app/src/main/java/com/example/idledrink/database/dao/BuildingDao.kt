package com.example.idledrink.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.idledrink.database.entity.BUILDING_TABLE_NAME
import com.example.idledrink.database.entity.BuildingEntity

@Dao
abstract class BuildingDao {

    @Query("SELECT * from $BUILDING_TABLE_NAME WHERE id = :id")
    abstract fun getBuildingsFromId(id: Long): BuildingEntity


    @Query("SELECT * from $BUILDING_TABLE_NAME WHERE id = :id")
    abstract fun getBuildingsFromIdAsLiveDate(id: Long): LiveData<BuildingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(buildings: BuildingEntity)
}
package com.example.idledrink.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val STAT_TABLE = "stats_table"

@Entity(tableName = STAT_TABLE)
data class StatEntity(@PrimaryKey(autoGenerate = false) val id: Long,
                      var at: Float = 0f,
                      var cl: Float = 0f,
                      var sv: Float = 150f) {
}
package com.example.idledrink.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.idledrink.database.dao.BuildingDao
import com.example.idledrink.database.dao.StatsDao
import com.example.idledrink.database.entity.BUILDING_TABLE_NAME
import com.example.idledrink.database.entity.BuildingEntity
import com.example.idledrink.database.entity.StatEntity

@Database(entities = arrayOf(BuildingEntity::class, StatEntity::class), version = 3, exportSchema = false)
public abstract class BuildingDataBase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

    abstract fun statDao(): StatsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BuildingDataBase? = null

        fun getDatabase(context: Context): BuildingDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BuildingDataBase::class.java,
                    "b_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("DROP TABLE $BUILDING_TABLE_NAME")
                database.execSQL("CREATE TABLE $BUILDING_TABLE_NAME (`id` LONG, `distilery` INTEGER, " +
                        "`distilertEmployees` INTEGER, `inhibiter` INTEGER, `inhibiterEmployees` INTEGER, " +
                        "`svfactory` INTEGER, `svfactoryEmployees` INTEGER, `geneticlab` INTEGER, " +
                        "`geneticlabEmployees` INTEGER, `alchooltank` INTEGER, `alchooltankEmployees` INTEGER, " +
                        "`svwarehouse` INTEGER, `svwarehouseEmployees` INTEGER, " +
                        "PRIMARY KEY(`id`))")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
            }
        }
    }
}
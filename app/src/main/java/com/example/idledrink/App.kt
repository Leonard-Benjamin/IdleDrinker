package com.example.idledrink

import android.app.Application
import com.example.idledrink.database.BuildingDataBase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy { import(androidXModule(this@App))

        bind() from singleton { instance<BuildingDataBase>().buildingDao()}
        bind() from singleton { instance<BuildingDataBase>().statDao()}


    }



}
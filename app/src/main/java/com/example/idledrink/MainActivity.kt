package com.example.idledrink

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.idledrink.database.BuildingDataBase
import com.example.idledrink.database.dao.StatsDao
import com.example.idledrink.database.entity.StatEntity
import com.example.idledrink.database.repository.StatsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var SVButton: Button
    lateinit var CLButton: Button
    lateinit var ATButton: Button
    val playerStats: PlayerStatsManager = PlayerStatsManager.instance
    lateinit var statDao: StatsDao
    lateinit var statRepository: StatsRepository
    lateinit var stats: StatEntity


    private val mainHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printTime()
        SVButton = findViewById(R.id.sousverre)
        CLButton = findViewById(R.id.tokill)
        ATButton = findViewById(R.id.alctaux)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setBackgroundColor(resources.getColor(R.color.darkGray))
        navView.selectedItemId
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_build, R.id.navigation_dashboard, R.id.navigation_attack, R.id.navigation_shop))
        navView.setupWithNavController(navController)

        this.statDao = BuildingDataBase.getDatabase(application).statDao()
        this.statRepository = StatsRepository(statDao)
        if (statRepository.getStats(1) == null) {
            statRepository.insert(StatEntity(1))
            stats = statRepository.getStats(1)
        }
        stats = statRepository.getStats(1)

        updateStatsValues(stats)

        SVButton.setOnClickListener {
            playerStats.SV += 1f
            updateSV()
        }

        CLButton.setOnClickListener {
            playerStats.CL += 0.1f
            updateCL()
        }

        ATButton.setOnClickListener {
        }

        mainHandler.post(object : Runnable {
            override fun run() {
                runOnUiThread {
                    playerStats.SV += playerStats.SVPerSecond
                    playerStats.CL += playerStats.CLPerSecond
                    playerStats.AT += playerStats.ATAddPerSecond - playerStats.ATRetPerSecond
                    updateStatsDisplay()
                }
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    fun updateStatsValues(statsEntity: StatEntity) {
        playerStats.SV = statsEntity.sv
        playerStats.CL = statsEntity.cl
        playerStats.AT = statsEntity.at
    }

    fun updateStatsDisplay() {
        updateALCTaux()
        updateCL()
        updateSV()
    }

    private fun updateALCTaux() {
        ATButton.text = Utils.getATPercentFromValues(playerStats.AT, playerStats.ATAddPerSecond,
            playerStats.ATRetPerSecond, playerStats.playerATLimit, afterComa = 2) + " %"
    }

    private fun updateCL() {
        CLButton.text = Utils.getCuttedString(value = playerStats.CL) + " CL"
    }

    private fun updateSV() {
        SVButton.text = Utils.getCuttedString(playerStats.SV) + " SV"
    }

    private fun printTime() {
        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        val hours: Int = cal.get(Calendar.HOUR_OF_DAY)
        val min: Int = cal.get(Calendar.MINUTE)
        val sec: Int = cal.get(Calendar.SECOND)
        Log.d("TIME", hours.toString() + " : " + min.toString() + "," + sec.toString())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        this.stats = statRepository.getStats(1)
        updateStatsValues(this.stats)
    }

    override fun onStop() {
        super.onStop()
        this.statRepository.insert(StatEntity(1, playerStats.AT, playerStats.CL, playerStats.SV))
    }
}
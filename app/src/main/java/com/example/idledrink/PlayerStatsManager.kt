package com.example.idledrink

class PlayerStatsManager {
    private object HOLDER {
        val INSTANCE = PlayerStatsManager()
    }

    companion object {
        val instance: PlayerStatsManager by lazy { HOLDER.INSTANCE }
    }

    var AT: Float = 0.0f
    var CL: Float = 0f
    var SV: Float = 15000f

    var playerATLimit = 50f

    var ATAddPerSecond = 0f
    var ATRetPerSecond = 0.01f

    var CLPerSecond = 0f

    var SVPerSecond = 0f
}
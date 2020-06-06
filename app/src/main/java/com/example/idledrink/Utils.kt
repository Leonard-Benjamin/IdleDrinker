package com.example.idledrink

import android.content.Context
import android.content.SharedPreferences
import android.view.contentcapture.ContentCaptureCondition
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.generic.contextTranslator
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object FloatToString {

        fun getCuttedString(value: Float, afterComa: Int = 1): String {
            val splited = value.toString().split(".")
            if (splited[1].length < 2) {
                return splited[0] + "," + splited[1].substring(0, 1)
            }
            return splited[0] + "," + splited[1].substring(0, afterComa)
        }

        fun getATPercentFromValues(
            currentAT: Float,
            addAT: Float,
            substrctAT: Float,
            playerLimit: Float,
            afterComa: Int = 1
        ): String {
            //TODO correct
            val percent = ((currentAT + addAT - substrctAT) / playerLimit) * 100
            return if (percent >= 0) getCuttedString(percent, afterComa) else "0"
        }

        fun writeSharedPreferenceString(tag: String, value: String, context: Context) {
            val prefs = context.getSharedPreferences("com.mycompany.myAppName",
                AppCompatActivity.MODE_PRIVATE
            )
            prefs.edit().putString(tag, value).apply()

        }

        fun getSharedPrefString(tag: String, context: Context): String {
            val prefs = context.getSharedPreferences("com.mycompany.myAppName",
                AppCompatActivity.MODE_PRIVATE
            )
            prefs.getString(tag, null)?.let { return it }
            return ""
        }

        fun getDateFromMillis(timeStamp: Long): String? {
            val dateFormat = SimpleDateFormat("MM-dd hh:mm")
            dateFormat.timeZone = TimeZone.getTimeZone("GMT+2:00")
            val date = dateFormat.format(timeStamp)
            val splited = date.split("-")
            val month = getMonth(splited[0])
            return month + " " + splited[1]
        }

        private fun getMonth(monthNumber: String): String {
            return when(monthNumber) {
                "01" -> "Jan"
                "02" -> "Fec"
                "03" -> "MAr"
                "04" -> "Avr"
                "05" -> "Mai"
                "06" -> "Juin"
                "07" -> "Juil"
                "08" -> "Aout"
                "09" -> "Sept"
                "10" -> "OCt"
                "11" -> "Nov"
                "12" -> "Dec"
                else -> "Unknown"
            }
        }

        inline fun <W : ViewModel> viewModelFactory(crossinline f: () -> W) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
            }
        }
}
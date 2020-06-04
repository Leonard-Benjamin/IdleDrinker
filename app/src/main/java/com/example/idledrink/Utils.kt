package com.example.idledrink

import android.content.Context
import android.content.SharedPreferences
import android.view.contentcapture.ContentCaptureCondition
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.generic.contextTranslator

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

        inline fun <W : ViewModel> viewModelFactory(crossinline f: () -> W) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
            }
        }
}
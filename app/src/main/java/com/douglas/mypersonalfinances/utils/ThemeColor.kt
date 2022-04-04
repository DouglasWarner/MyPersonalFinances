package com.douglas.mypersonalfinances.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.annotation.ColorInt
import kotlin.math.roundToInt


class ThemeColors(context: Context) {

    @ColorInt
    var color: Int

    // Checking if title text color will be black
    private val isLightActionBar: Boolean
        private get() { // Checking if title text color will be black
            val rgb: Int = (Color.red(color) + Color.green(color) + Color.blue(color)) / 3
            return rgb > 210
        }

    companion object {
        private const val NAME = "ThemeColors"
        private const val KEY = "color"
        fun setNewThemeColor(activity: Activity, red: Int, green: Int, blue: Int) {
            var red = red
            var green = green
            var blue = blue
            val colorStep = 15
            red = (red / colorStep).toFloat().roundToInt() * colorStep
            green = (green / colorStep).toFloat().roundToInt() * colorStep
            blue = (blue / colorStep).toFloat().roundToInt() * colorStep
            val stringColor = Integer.toHexString(Color.rgb(red, green, blue)).substring(2)
            val editor = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
            editor.putString(KEY, stringColor)
            editor.apply()
            val i = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
            i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(i)
        }
    }

    init {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val stringColor = sharedPreferences.getString(KEY, "004bff")
        color = Color.parseColor("#$stringColor")
//        if (isLightActionBar) context.setTheme(R.style.Theme)
        context.setTheme(
            context.resources
                .getIdentifier("T_$stringColor", "style", context.packageName)
        )
    }
}
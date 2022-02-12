package com.mona_daigle.diet_meal_plan.upgrade

import android.app.Activity
import android.content.Context

class UpgradeManager {
    var isPremium = false

    companion object {
        private const val PREMIUM_1_WEEK = "dietmealplan_1_week"
        private const val PREMIUM_1_MONTH = "dietmealplan_1_month"
        private const val PREMIUM_3_MONTH = "dietmealplan_3_month"
        private const val PREMIUM_6_MONTH = "dietmealplan_6_month"
        private const val PREMIUM_1_YEAR = "dietmealplan_1_year"

        private const val BUY_69_DIAMONDS = "dietmealplan_69_diamonds"
        private const val BUY_139_DIAMONDS = "dietmealplan_139_diamonds"
        private const val BUY_349_DIAMONDS = "dietmealplan_349_diamonds"
        private const val BUY_699_DIAMONDS = "dietmealplan_699_diamonds"
        private const val BUY_3499_DIAMONDS = "dietmealplan_3499_diamonds"
        private const val BUY_6999_DIAMONDS = "dietmealplan_6999_diamonds"

        val premiumIds = listOf(
//            "android.test.purchased",
            PREMIUM_1_WEEK,
            PREMIUM_1_MONTH,
            PREMIUM_3_MONTH,
            PREMIUM_6_MONTH,
            PREMIUM_1_YEAR,
        )

        val diamondIds = listOf(
//            "android.test.purchased",
            BUY_69_DIAMONDS,
            BUY_139_DIAMONDS,
            BUY_349_DIAMONDS,
            BUY_699_DIAMONDS,
            BUY_3499_DIAMONDS,
            BUY_6999_DIAMONDS,
        )

        private var instance: UpgradeManager? = null
        fun getInstance(): UpgradeManager? {
            val packageName = this.javaClass.getPackage().name
            prefsName = if (packageName == null) {
                "upgrade_manager_shared_pref"
            } else {
                packageName + "shared_pref"
            }
            if (instance == null) {
                instance = UpgradeManager()
            }
            return instance
        }

        private var prefsName: String? = null
    }

    fun getDiamond(activity: Activity): Int {
        val sharedPreferences = activity.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return sharedPreferences.getInt("diamond", 0)
    }

    fun incrementDiamond(activity: Activity, value: Int) {
        val sharedPreferences = activity.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("diamond", getDiamond(activity) + value).apply()
    }

    fun decrementDiamond(activity: Activity, value: Int) {
        val sharedPreferences = activity.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val currentDiamond = getDiamond(activity)
        if (currentDiamond >= value) {
            sharedPreferences.edit().putInt("diamond", currentDiamond - value).apply()
        }
    }
}
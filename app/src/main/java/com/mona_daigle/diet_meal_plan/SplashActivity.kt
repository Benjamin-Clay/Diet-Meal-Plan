package com.mona_daigle.diet_meal_plan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mona_daigle.diet_meal_plan.database.AppDatabase
import com.mona_daigle.diet_meal_plan.models.dataMeals

class SplashActivity : AppCompatActivity() {
    private val appDatabase by lazy {
        AppDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (appDatabase.getMeals(null, null).isEmpty()) {
            appDatabase.insertMeals(dataMeals)
        }

        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }

}
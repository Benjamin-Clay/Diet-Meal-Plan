package com.mona_daigle.diet_meal_plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona_daigle.diet_meal_plan.databinding.ActivityMainBinding
import com.mona_daigle.diet_meal_plan.fragments.FavouriteFragment
import com.mona_daigle.diet_meal_plan.fragments.HomeFragment
import com.mona_daigle.diet_meal_plan.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, HomeFragment.newInstance()).commit()

        binding.bottomBar.onItemSelected = {
            when (it) {
                0 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, HomeFragment.newInstance()).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, SearchFragment.newInstance()).commit()
                }
                else -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, FavouriteFragment.newInstance()).commit()
                }
            }
        }
    }

}
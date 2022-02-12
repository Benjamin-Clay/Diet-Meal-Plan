package com.mona_daigle.diet_meal_plan.upgrade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona_daigle.diet_meal_plan.R
import com.mona_daigle.diet_meal_plan.databinding.ActivityUpgradeBinding

class UpgradeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpgradeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpgradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabBack.setOnClickListener { onBackPressed() }

//        binding.tabLayout.addOnTabSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, DiamondFragment.newInstance()).commit()

    }

//    override fun onTabSelected(tab: TabLayout.Tab?) {
//        if (tab?.text == "Diamond") {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, DiamondFragment.newInstance()).commit()
//        } else {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, PremiumFragment.newInstance()).commit()
//        }
//    }
//
//    override fun onTabUnselected(tab: TabLayout.Tab?) {
//    }
//
//    override fun onTabReselected(tab: TabLayout.Tab?) {
//    }
}
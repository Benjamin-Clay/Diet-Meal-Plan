package com.mona_daigle.diet_meal_plan.upgrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mona_daigle.diet_meal_plan.R
import com.mona_daigle.diet_meal_plan.databinding.ActivityUpgradeBinding
import com.google.android.material.tabs.TabLayout
import com.mona_daigle.diet_meal_plan.upgrade.DiamondFragment
import com.mona_daigle.diet_meal_plan.upgrade.PremiumFragment

class UpgradeActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: ActivityUpgradeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpgradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { onBackPressed() }

        binding.tabLayout.addOnTabSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, DiamondFragment.newInstance()).commit()

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab?.text == "Diamond") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DiamondFragment.newInstance()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PremiumFragment.newInstance()).commit()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }
}
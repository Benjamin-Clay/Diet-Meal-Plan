package com.mona_daigle.diet_meal_plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mona_daigle.diet_meal_plan.databinding.ActivityDetailBinding
import com.mona_daigle.diet_meal_plan.models.Meal

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val meal = intent.getSerializableExtra("meal") as Meal
        with(binding) {
            ivMeal.setImageResource(meal.imageMeal)
            tvTitleMeal.text = meal.titleMeal
//            tvPriceMeal.text = "Price: ${meal.priceMeal}"
            tvDesMeal.text = "${meal.desMeal}"

            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}
package com.mona_daigle.diet_meal_plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mona_daigle.diet_meal_plan.database.AppDatabase
import com.mona_daigle.diet_meal_plan.databinding.ActivityEditBinding
import com.mona_daigle.diet_meal_plan.models.Meal

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val appDatabase by lazy {
        AppDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        val meal: Meal = intent.getSerializableExtra("meal") as Meal
        binding.etTitle.setText(meal.titleMeal)
        binding.etDes.setText(meal.desMeal)
        binding.imageView.setImageResource(meal.imageMeal)

        binding.btSave.setOnClickListener {
            val title: String = binding.etTitle.text.toString()
            val des: String = binding.etDes.text.toString()
//            val price: String = binding.etPrice.text.toString()
            if (title.isNotEmpty() && des.isNotEmpty()) {
                appDatabase.updateMeal(Meal(meal.imageMeal, title, "", des))
                Snackbar.make(binding.root, "Success", 700)
                    .setBackgroundTint(ContextCompat.getColor(this, R.color.green)).show()
            }
        }

    }
}
package com.mona_daigle.diet_meal_plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mona_daigle.diet_meal_plan.database.AppDatabase
import com.mona_daigle.diet_meal_plan.databinding.ActivityAddBinding
import com.mona_daigle.diet_meal_plan.models.Meal
import com.google.android.material.snackbar.Snackbar

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val appDatabase by lazy {
        AppDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btSave.setOnClickListener {
            val title: String = binding.etTitle.text.toString()
            val des: String = binding.etDes.text.toString()
//            val price: String = binding.etPrice.text.toString()
            if (title.isNotEmpty() && des.isNotEmpty()) {
                appDatabase.insertMeal(Meal(R.drawable.ic_logo, title, "", des))
                Snackbar.make(binding.root, "Success", 700)
                    .setBackgroundTint(ContextCompat.getColor(this, R.color.green)).show()
            }
        }

    }
}
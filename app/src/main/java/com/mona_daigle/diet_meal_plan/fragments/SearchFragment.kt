package com.mona_daigle.diet_meal_plan.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.mona_daigle.diet_meal_plan.DetailActivity
import com.mona_daigle.diet_meal_plan.EditActivity
import com.mona_daigle.diet_meal_plan.adapters.MealAdapter
import com.mona_daigle.diet_meal_plan.database.AppDatabase
import com.mona_daigle.diet_meal_plan.databinding.DialogDeleteBinding
import com.mona_daigle.diet_meal_plan.databinding.FragmentSearchBinding
import com.mona_daigle.diet_meal_plan.models.Meal

class SearchFragment : Fragment(), MealAdapter.OnItemMealClickListener, TextWatcher {
    private lateinit var binding: FragmentSearchBinding
    private val appDatabase by lazy {
        AppDatabase(requireActivity())
    }
    private val mealAdapter by lazy {
        MealAdapter(requireActivity(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.addTextChangedListener(this)

        binding.rvMeal.adapter = mealAdapter
        mealAdapter.meals = appDatabase.getMeals(null, null)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemMealClick(index: Int, meal: Meal) {
        startActivity(Intent(requireActivity(), DetailActivity::class.java).putExtra("meal", meal))
    }

    override fun onItemMealClickFavourite(index: Int, meal: Meal) {
        if (meal.isLikeMeal == 0) {
            meal.isLikeMeal = 1
        } else {
            meal.isLikeMeal = 0
        }
        appDatabase.updateMeal(meal)
        mealAdapter.meals = appDatabase.getMeals(null, null)

    }

    override fun onItemMealClickEdit(index: Int, meal: Meal) {
        startActivity(Intent(requireActivity(), EditActivity::class.java).putExtra("meal", meal))
    }

    override fun onItemMealClickDelete(index: Int, meal: Meal) {
        val dialogDeleteBinding: DialogDeleteBinding = DialogDeleteBinding.inflate(LayoutInflater.from(requireActivity()), null, false)
        val dialog: Dialog = Dialog(requireActivity())
        dialog.setContentView(dialogDeleteBinding.root)

        val window: Window? = dialog.window
        window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        dialog.show()

        dialogDeleteBinding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.tvOK.setOnClickListener {
            dialog.dismiss()
            appDatabase.deleteMeal(meal)
            mealAdapter.meals = appDatabase.getMeals(null, null)
        }
    }

    override fun onStart() {
        super.onStart()
        mealAdapter.meals = appDatabase.getMeals(null, null)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val search: String = p0.toString()
        if (search.isEmpty()) {
            mealAdapter.meals = appDatabase.getMeals(null, null)
            return
        }
        mealAdapter.meals = appDatabase.getMeals("titleMeal like ?", arrayOf("%$search%"))
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}
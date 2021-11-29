package com.mona_daigle.diet_meal_plan.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.mona_daigle.diet_meal_plan.R
import com.mona_daigle.diet_meal_plan.databinding.ItemMealBinding
import com.mona_daigle.diet_meal_plan.models.Meal

class MealAdapter(private val context: Context, private val onItemMealClickListener: OnItemMealClickListener) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    var meals = emptyList<Meal>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]

        val popupMenu: PopupMenu = PopupMenu(context, holder.binding.ivOption)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itemEdit -> {
                    onItemMealClickListener.onItemMealClickEdit(position, meal)
                }
                R.id.itemDelete -> {
                    onItemMealClickListener.onItemMealClickDelete(position, meal)
                }
            }
            true
        }

        with(holder.binding) {
            tvTitleMeal.text = meal.titleMeal
//            tvPriceMeal.text = "Price: ${meal.priceMeal}"
            tvDesMeal.text = "${meal.desMeal}"
            ivMeal.setImageResource(meal.imageMeal)

            if (meal.isLikeMeal == 0) {
                ivFavourite.setImageResource(R.drawable.ic_not_favorite)
            } else {
                ivFavourite.setImageResource(R.drawable.ic_favorite)
            }

            ivFavourite.setOnClickListener {
                onItemMealClickListener.onItemMealClickFavourite(position, meal)
            }

            ivOption.setOnClickListener {
                popupMenu.show()
            }
            root.setOnClickListener {
                onItemMealClickListener.onItemMealClick(position, meal)
            }
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    interface OnItemMealClickListener {
        fun onItemMealClick(index: Int, meal: Meal)
        fun onItemMealClickFavourite(index: Int, meal: Meal)
        fun onItemMealClickEdit(index: Int, meal: Meal)
        fun onItemMealClickDelete(index: Int, meal: Meal)
    }
}
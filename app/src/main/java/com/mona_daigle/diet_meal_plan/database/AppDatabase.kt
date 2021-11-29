package com.mona_daigle.diet_meal_plan.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mona_daigle.diet_meal_plan.models.Meal


class AppDatabase(context: Context) : SQLiteOpenHelper(context, "app_meal_database", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        private const val SQL_CREATE_ENTRIES =
            "create table meals (" +
                    "imageMeal integer," +
                    "titleMeal text primary key," +
                    "priceMeal text," +
                    "desMeal text," +
                    "isLikeMeal text)"
        private const val SQL_DELETE_ENTRIES = "drop table if exists meals"
    }

    fun insertMeal(meal: Meal) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("imageMeal", meal.imageMeal)
            put("titleMeal", meal.titleMeal)
            put("priceMeal", meal.priceMeal)
            put("desMeal", meal.desMeal)
            put("isLikeMeal", meal.isLikeMeal)
        }
        db.insert("meals", null, values)
        db.close()
    }

    fun insertMeals(meals: List<Meal>) {
        val db = this.writableDatabase
        for (meal in meals) {
            val values = ContentValues().apply {
                put("imageMeal", meal.imageMeal)
                put("titleMeal", meal.titleMeal)
                put("priceMeal", meal.priceMeal)
                put("desMeal", meal.desMeal)
                put("isLikeMeal", meal.isLikeMeal)
            }
            db.insert("meals", null, values)
        }
        db.close()
    }

    fun updateMeal(meal: Meal) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("imageMeal", meal.imageMeal)
            put("titleMeal", meal.titleMeal)
            put("priceMeal", meal.priceMeal)
            put("desMeal", meal.desMeal)
            put("isLikeMeal", meal.isLikeMeal)
        }
        db.update("meals", values, "titleMeal = ?", arrayOf(meal.titleMeal))
        db.close()
    }

    fun deleteMeal(meal: Meal) {
        val db = this.writableDatabase
        db.delete("meals", "titleMeal = ?", arrayOf(meal.titleMeal))
        db.close()
    }

    fun getMeals(selection: String?, selectionArgs: Array<String>?): List<Meal> {
        val db = this.readableDatabase

        val cursor = db.query(
            "meals",
            null,
            selection,
            selectionArgs,
            null,
            null,
            null,
        )

        val meals = mutableListOf<Meal>()
        with(cursor) {
            while (moveToNext()) {
                val meal = Meal(
                    getInt(getColumnIndexOrThrow("imageMeal")),
                    getString(getColumnIndexOrThrow("titleMeal")),
                    getString(getColumnIndexOrThrow("priceMeal")),
                    getString(getColumnIndexOrThrow("desMeal")),
                    getInt(getColumnIndexOrThrow("isLikeMeal")),
                )
                meals.add(meal)
            }
        }
        cursor.close()
        db.close()
        return meals
    }

}
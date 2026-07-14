package com.example.nutrition.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nutrition.data.model.Meal
import com.example.nutrition.data.model.User
import com.example.nutrition.data.model.TrainingData

@Database(
    entities = [User::class, Meal::class, TrainingData::class],
    version = 1,
    exportSchema = false
)
abstract class NutritionDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun userDao(): UserDao
    abstract fun trainingDao(): TrainingDao
}

package com.example.nutrition.data.repository

import com.example.nutrition.data.model.Meal
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun addMeal(meal: Meal)
    suspend fun getMeal(id: Int): Meal?
    suspend fun deleteMeal(id: Int)
    fun getAllMeals(): Flow<List<Meal>>
    fun getMealsByDate(date: Long): Flow<List<Meal>>
}

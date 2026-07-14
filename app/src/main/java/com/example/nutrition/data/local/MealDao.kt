package com.example.nutrition.data.local

import androidx.room.*
import com.example.nutrition.data.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: Int): Meal?

    @Query("SELECT * FROM meals ORDER BY date DESC")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getMealsByDateRange(startDate: Long, endDate: Long): Flow<List<Meal>>
}

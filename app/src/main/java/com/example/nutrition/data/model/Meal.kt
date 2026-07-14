package com.example.nutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName: String = "",
    val quantity: Double = 0.0, // グラム
    val calories: Double = 0.0,
    val protein: Double = 0.0,  // g
    val carbs: Double = 0.0,    // g
    val fat: Double = 0.0,      // g
    val mealTime: String = "", // "breakfast", "lunch", "dinner", "snack"
    val date: Long = System.currentTimeMillis()
)

package com.example.nutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_data")
data class TrainingData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exerciseType: String = "", // "strength", "cardio", "flexibility"
    val duration: Int = 0,          // 分
    val intensity: String = "",    // "low", "moderate", "high"
    val caloriesBurned: Double = 0.0,
    val date: Long = System.currentTimeMillis()
)

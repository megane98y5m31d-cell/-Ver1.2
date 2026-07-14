package com.example.nutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val age: Int = 0,
    val gender: String = "", // "M" or "F"
    val heightCm: Double = 0.0,
    val weightKg: Double = 0.0,
    val goal: String = "", // "bulking", "cutting", "maintenance"
    val activityLevel: String = "", // "sedentary", "light", "moderate", "very_active"
    val createdAt: Long = System.currentTimeMillis()
)

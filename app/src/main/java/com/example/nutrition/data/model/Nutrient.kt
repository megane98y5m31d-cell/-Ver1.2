package com.example.nutrition.data.model

data class Nutrient(
    val name: String = "",
    val value: Double = 0.0,
    val unit: String = "", // "g", "mg", "mcg", "kcal"
    val recommendedDaily: Double = 0.0,
    val percentOfDaily: Double = 0.0
)

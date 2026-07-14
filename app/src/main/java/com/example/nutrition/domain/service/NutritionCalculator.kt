package com.example.nutrition.domain.service

import com.example.nutrition.data.model.Meal
import com.example.nutrition.data.model.Nutrient
import javax.inject.Singleton

@Singleton
class NutritionCalculator {

    /**
     * 食事リストから総栄養素を計算
     */
    fun calculateTotalNutrients(meals: List<Meal>): Map<String, Double> {
        return mapOf(
            "calories" to meals.sumOf { it.calories },
            "protein" to meals.sumOf { it.protein },
            "carbs" to meals.sumOf { it.carbs },
            "fat" to meals.sumOf { it.fat }
        )
    }

    /**
     * 推奨摂取量を計算（Mifflin-St Jeor式）
     */
    fun calculateBMR(
        weightKg: Double,
        heightCm: Double,
        ageYears: Int,
        gender: String // "M" or "F"
    ): Double {
        return if (gender == "M") {
            10 * weightKg + 6.25 * heightCm - 5 * ageYears + 5
        } else {
            10 * weightKg + 6.25 * heightCm - 5 * ageYears - 161
        }
    }

    /**
     * 活動レベルに基づいてTDEEを計算
     */
    fun calculateTDEE(
        bmr: Double,
        activityLevel: String
    ): Double {
        val multiplier = when (activityLevel) {
            "sedentary" -> 1.2
            "light" -> 1.375
            "moderate" -> 1.55
            "very_active" -> 1.725
            else -> 1.55
        }
        return bmr * multiplier
    }

    /**
     * ボディメイク目的に応じた推奨栄養素を計算
     */
    fun calculateRecommendedMacros(
        tdee: Double,
        goal: String,
        weightKg: Double
    ): Map<String, Double> {
        return when (goal) {
            "bulking" -> mapOf(
                "calories" to tdee + 300,
                "protein" to weightKg * 2.0,      // 1kg当たり2g
                "carbs" to (tdee + 300) * 0.5 / 4, // 50%
                "fat" to (tdee + 300) * 0.2 / 9    // 20%
            )
            "cutting" -> mapOf(
                "calories" to tdee - 300,
                "protein" to weightKg * 2.2,       // 1kg当たり2.2g
                "carbs" to (tdee - 300) * 0.4 / 4, // 40%
                "fat" to (tdee - 300) * 0.25 / 9   // 25%
            )
            else -> mapOf( // maintenance
                "calories" to tdee,
                "protein" to weightKg * 1.6,
                "carbs" to tdee * 0.45 / 4,
                "fat" to tdee * 0.25 / 9
            )
        }
    }
}

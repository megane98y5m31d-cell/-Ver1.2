package com.example.nutrition.domain.service

import com.example.nutrition.data.model.Meal
import javax.inject.Singleton

@Singleton
class AdviceGenerator {

    /**
     * 栄養バランスに基づいてAIアドバイスを生成
     * 実装時にClaude API と統合予定
     */
    fun generateAdvice(
        meals: List<Meal>,
        recommendedMacros: Map<String, Double>,
        goal: String
    ): String {
        val totalNutrients = meals.groupingBy { it.foodName }.eachCount()
        
        return when {
            recommendedMacros["protein"]?.let { meals.sumOf { it.protein } < it * 0.8 } == true ->
                "タンパク質が不足しています。鶏肉、卵、豆類などを増やしましょう。"
            goal == "bulking" && (meals.sumOf { it.calories } < (recommendedMacros["calories"] ?: 0.0) * 0.9) ->
                "目標カロリーに達していません。より多くの栄養価の高い食事を心がけましょう。"
            goal == "cutting" && (meals.sumOf { it.calories } > (recommendedMacros["calories"] ?: 0.0) * 1.1) ->
                "目標カロリーを超えています。低カロリー食を選択しましょう。"
            else ->
                "栄養バランスが良好です。このペースを保ちましょう。"
        }
    }
}

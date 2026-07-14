package com.example.nutrition.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutrition.data.local.NutritionDatabase
import com.example.nutrition.data.model.Meal
import com.example.nutrition.domain.service.NutritionCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val database: NutritionDatabase,
    private val calculator: NutritionCalculator
) : ViewModel() {

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals.asStateFlow()

    private val _totalNutrients = MutableStateFlow<Map<String, Double>>(emptyMap())
    val totalNutrients: StateFlow<Map<String, Double>> = _totalNutrients.asStateFlow()

    init {
        loadMeals()
    }

    private fun loadMeals() {
        viewModelScope.launch {
            database.mealDao().getAllMeals().collect { mealList ->
                _meals.value = mealList
                _totalNutrients.value = calculator.calculateTotalNutrients(mealList)
            }
        }
    }

    fun addMeal(foodName: String, quantity: Double) {
        viewModelScope.launch {
            val meal = Meal(
                foodName = foodName,
                quantity = quantity,
                calories = 0.0,  // APIから取得する処理を追加予定
                protein = 0.0,
                carbs = 0.0,
                fat = 0.0
            )
            database.mealDao().insertMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            database.mealDao().deleteMeal(meal)
        }
    }
}

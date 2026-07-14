package com.example.nutrition.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutrition.ui.screens.HomeScreen
import com.example.nutrition.ui.screens.MealInputScreen

@Composable
fun NutritionApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("meal_input") {
            MealInputScreen(navController = navController)
        }
    }
}

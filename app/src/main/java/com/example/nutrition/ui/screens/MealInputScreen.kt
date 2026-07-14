package com.example.nutrition.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nutrition.viewmodel.NutritionViewModel

@Composable
fun MealInputScreen(
    navController: NavController,
    viewModel: NutritionViewModel = hiltViewModel()
) {
    var mealName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("食事を記録") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "戻る")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = mealName,
                onValueChange = { mealName = it },
                label = { Text("食事内容") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("量（g）") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Button(
                onClick = {
                    if (mealName.isNotEmpty() && quantity.isNotEmpty()) {
                        viewModel.addMeal(mealName, quantity.toDoubleOrNull() ?: 0.0)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("記録する")
            }
        }
    }
}

package com.usfuchsia.mvvmrecipecompose.presentation.recipe_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController

@Composable
fun RecipeListScreen(navController: NavController, viewModel: RecipeListViewModel) {

    LaunchedEffect(key1 = true, block ={
        viewModel.recipes
    })
}
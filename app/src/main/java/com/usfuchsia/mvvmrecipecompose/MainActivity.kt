package com.usfuchsia.mvvmrecipecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.usfuchsia.mvvmrecipecompose.presentation.navigation.Screen
import com.usfuchsia.mvvmrecipecompose.presentation.recipe_list.RecipeListScreen
import com.usfuchsia.mvvmrecipecompose.presentation.recipe_list.RecipeListViewModel
import com.usfuchsia.mvvmrecipecompose.ui.RecipeDetailScreen
import com.usfuchsia.mvvmrecipecompose.ui.theme.MVVMRecipeComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRecipeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val recipeListViewModel: RecipeListViewModel by viewModels()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.RecipeListScreen.route
                    ) {
                        composable(route = Screen.RecipeListScreen.route) { navBackStackEntry ->
                            RecipeListScreen(
                                navController = navController,
                                viewModel = recipeListViewModel
                            )
                        }
                        composable(Screen.RecipeDetailScreen.route) {
                            RecipeDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


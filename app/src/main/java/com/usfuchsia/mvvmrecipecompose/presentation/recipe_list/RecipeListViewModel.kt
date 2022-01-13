package com.usfuchsia.mvvmrecipecompose.presentation.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usfuchsia.mvvmrecipecompose.domain.Recipe
import com.usfuchsia.mvvmrecipecompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(

    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    init {
        viewModelScope.launch {
           val recipes= recipeRepository.search(token,1,"beef carrot potato onion")
            for(recipe in recipes){
                Log.e("RecipeListViewModel",recipe.title)
            }
        }
    }
}
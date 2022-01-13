package com.usfuchsia.mvvmrecipecompose.repository

import com.usfuchsia.mvvmrecipecompose.domain.Recipe


interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>


    suspend fun getRecipeById(token: String, id: String): Recipe
}
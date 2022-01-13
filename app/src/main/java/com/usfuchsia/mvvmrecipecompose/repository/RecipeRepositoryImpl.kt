package com.usfuchsia.mvvmrecipecompose.repository

import com.usfuchsia.mvvmrecipecompose.domain.Recipe
import com.usfuchsia.mvvmrecipecompose.network.RetrofitService

class RecipeRepositoryImpl(
    private val retrofitService: RetrofitService
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return retrofitService.search(token = token, page = page, query = query).results
    }

    override suspend fun getRecipeById(token: String, id: String): Recipe {
        return retrofitService.getRecipeById(token = token, id = id)
    }
}
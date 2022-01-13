package com.usfuchsia.mvvmrecipecompose.network

import com.usfuchsia.mvvmrecipecompose.domain.Recipe
import com.usfuchsia.mvvmrecipecompose.domain.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {

    @GET("search")
    suspend fun search(
        @Header("Authorization")
        token: String,
        @Query("page")
        page: Int,
        @Query("query")
        query: String
    ): RecipeListResponse

    @GET("get")
    suspend fun getRecipeById(
        @Header("Authorization")
        token: String,
        @Query("id")
        id: String,
    ): Recipe
}
package com.usfuchsia.mvvmrecipecompose.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.usfuchsia.mvvmrecipecompose.MainApplication
import com.usfuchsia.mvvmrecipecompose.network.RetrofitService
import com.usfuchsia.mvvmrecipecompose.repository.RecipeRepository
import com.usfuchsia.mvvmrecipecompose.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        coerceInputValues = true;ignoreUnknownKeys = true;

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
//            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RetrofitService,
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            retrofitService = recipeService,
        )
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication {
        return app as MainApplication
    }
}
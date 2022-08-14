package com.usfuchsia.mvvmrecipecompose.di

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RetrofitService,
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            retrofitService = recipeService,
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): RetrofitService {
        val contentType = "application/json".toMediaType()
        val json = Json {
            coerceInputValues = true;ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        logging: HttpLoggingInterceptor,
        chuckInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(chuckInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return logging
    }

    @Singleton
    @Provides
    fun provideChuckInterceptor(application: Application): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(application.applicationContext)
            .collector(ChuckerCollector(application.applicationContext))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
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
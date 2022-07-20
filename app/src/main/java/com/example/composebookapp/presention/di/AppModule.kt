package com.example.composebookapp.presention.di

import com.example.composebookapp.data.BookRepositoryImpl
import com.example.composebookapp.data.api.BooKApi
import com.example.composebookapp.domain.reposistory.BookRepository
import com.example.composebookapp.util.Constants
import org.conscrypt.BuildConfig


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(): BooKApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BAE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(BooKApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBookRepository(bookApi:BooKApi): BookRepository {
        return BookRepositoryImpl(bookApi)
    }

}

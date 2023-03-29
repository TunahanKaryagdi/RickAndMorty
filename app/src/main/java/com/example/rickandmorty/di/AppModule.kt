package com.example.rickandmorty.di

import android.content.Context
import android.content.SharedPreferences
import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.data.repository.RamRepositoryImpl
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.domain.use_cases.GetLocationUseCase
import com.example.rickandmorty.util.Constants.BASE_URL
import com.example.rickandmorty.util.Constants.PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideApi() : RickAndMortyApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRamRepository(api : RickAndMortyApi) : RamRepository{
        return RamRepositoryImpl(api)
    }


   @Provides
   @Singleton
    fun provideSharedPreference(@ApplicationContext context : Context) : SharedPreferences{
        return context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    }

}
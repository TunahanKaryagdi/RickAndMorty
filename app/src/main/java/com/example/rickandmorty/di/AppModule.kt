package com.example.rickandmorty.di

import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.data.repository.RamRepositoryImpl
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.domain.use_cases.GetLocationUseCase
import com.example.rickandmorty.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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



}
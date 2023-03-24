package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.LocationDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/location")
    suspend fun getLocation() : LocationDto

}
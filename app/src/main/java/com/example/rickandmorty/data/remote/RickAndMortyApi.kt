package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.LocationDto
import com.example.rickandmorty.data.model.ResidentDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyApi {

    @GET("api/location")
    suspend fun getLocation(@Query("page") page: Int) : LocationDto

    @GET("api/character/{ids}")
    suspend fun getResidentsByLocation(@Path("ids") ids : String) : List<ResidentDto>

    @GET("api/character/{id}")
    suspend fun getSingleResident(@Path("id") id: String) : ResidentDto

}
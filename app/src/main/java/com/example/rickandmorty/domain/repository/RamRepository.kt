package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.model.LocationDto
import com.example.rickandmorty.data.model.ResidentDto

interface RamRepository {

    suspend fun getLocation(page :Int) : LocationDto

    suspend fun getResidentsByLocation(ids :String) : List<ResidentDto>

    suspend fun getSingleResident(id: String) : ResidentDto

}
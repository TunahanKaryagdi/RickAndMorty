package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.model.LocationDto
import com.example.rickandmorty.data.model.ResidentDto

interface RamRepository {

    suspend fun getLocation() : LocationDto

    suspend fun getResidentsByLocation(ids :String) : List<ResidentDto>
}
package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.model.LocationDto

interface RamRepository {

    suspend fun getLocation() : LocationDto

}
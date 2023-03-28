package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.LocationDto
import com.example.rickandmorty.data.model.ResidentDto
import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.domain.repository.RamRepository
import javax.inject.Inject

class RamRepositoryImpl @Inject constructor(private val api: RickAndMortyApi) : RamRepository {

    override suspend fun getLocation(): LocationDto {
        return api.getLocation()
    }

    override suspend fun getResidentsByLocation(ids: String): List<ResidentDto> {
        return api.getResidentsByLocation(ids)
    }

    override suspend fun getSingleResident(id: String): ResidentDto {
        return api.getSingleResident(id)
    }
}
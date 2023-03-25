package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.data.model.toLocation
import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: RamRepository) {


    operator fun invoke() : Flow<Resource<List<Location>>>{
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.getLocation().toLocation()
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }



}
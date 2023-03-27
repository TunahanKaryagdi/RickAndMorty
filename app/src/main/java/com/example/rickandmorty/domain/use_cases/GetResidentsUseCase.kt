package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.data.model.toLocation
import com.example.rickandmorty.data.model.toResident
import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.model.Resident
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResidentsUseCase @Inject constructor(private val repository: RamRepository) {

    operator fun invoke(ids :String) : Flow<Resource<List<Resident>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val temp = repository.getResidentsByLocation(ids)
                val response  = mutableListOf<Resident>()
                temp.forEach{residentDto ->
                    response.add(residentDto.toResident())
                }
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }

}
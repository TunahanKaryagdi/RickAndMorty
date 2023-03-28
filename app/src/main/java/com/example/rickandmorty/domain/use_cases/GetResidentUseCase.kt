package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.data.model.toResident
import com.example.rickandmorty.data.model.toResidentDetail
import com.example.rickandmorty.domain.model.Resident
import com.example.rickandmorty.domain.model.ResidentDetail
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResidentUseCase @Inject constructor(private val repository: RamRepository) {

    operator fun invoke(id: String): Flow<Resource<ResidentDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.getSingleResident(id).toResidentDetail()
                emit(Resource.Success(response))

            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }

}
package com.example.rickandmorty.presentation.screens.detail

import android.hardware.lights.LightState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.ResidentDetail
import com.example.rickandmorty.domain.repository.RamRepository
import com.example.rickandmorty.domain.use_cases.GetResidentUseCase
import com.example.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getResidentUseCase: GetResidentUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var residentDetail by mutableStateOf<ResidentDetail?>(null)
        private set

    var loading by mutableStateOf(false)
        private set


    private val id: String = checkNotNull(savedStateHandle["id"])

    init {
        getResident()
    }

    private fun getResident() {

        viewModelScope.launch {

            getResidentUseCase(id).collect { resource ->

                when (resource) {

                    is Resource.Loading -> {
                        loading = true
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Success -> {

                        loading = false
                        residentDetail = resource.data
                    }
                }

            }
        }


    }


    fun fetchEpisodeFromUrl(urls :List<String>) :String{
        if(urls.isEmpty()){
            return "Unknown"
        }
        var episodes = ""
        urls.map{url->
            episodes += url.split("/").last()+","
        }
        return episodes
    }

}
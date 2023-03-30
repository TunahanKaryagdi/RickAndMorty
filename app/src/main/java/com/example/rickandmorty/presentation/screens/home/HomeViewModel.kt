package com.example.rickandmorty.presentation.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.model.Resident
import com.example.rickandmorty.domain.use_cases.GetLocationUseCase
import com.example.rickandmorty.domain.use_cases.GetResidentsUseCase
import com.example.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getResidentsUseCase: GetResidentsUseCase
) :
    ViewModel() {


    var locationList by mutableStateOf<List<Location>>(emptyList())
        private set

    var selectedLocation by mutableStateOf(0)
        private set

    var residentList by mutableStateOf<List<Resident>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    var lazyRowLoading = false

    var pageSize = 1

    init {

        getLocations()
        getResidents()
    }

    private fun getLocations() {


        viewModelScope.launch {

            getLocationUseCase.invoke(pageSize).collect { resource ->

                when (resource) {

                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Success -> {
                        locationList = resource.data ?: emptyList()

                    }
                }

            }

        }

    }


    private fun getResidents() {


        viewModelScope.launch {

            loading = true
            delay(1500)

            var ids = getIdsFromUrl()
            getResidentsUseCase(ids).collect { resource ->
                when (resource) {

                    is Resource.Loading -> {
                        loading = true
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Success -> {
                        loading = false
                        residentList = resource.data ?: emptyList()

                    }
                }
            }
        }

    }


    fun loadNewLocations() {

        lazyRowLoading =true
        pageSize +=1
        viewModelScope.launch {
            delay(1500)
            getLocationUseCase.invoke(pageSize).collect { resource ->

                when (resource) {

                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Success -> {
                        locationList += resource.data ?: emptyList()
                        lazyRowLoading = false

                    }
                }

            }
        }
    }


    fun updateSelectedLocation(id: Int) {

        if (id != selectedLocation) {
            selectedLocation = id
            getResidents()
        }
    }

    private fun getIdsFromUrl() : String{
        var ids = ""
        val residentsUrl =
            if (locationList.isNotEmpty()) locationList[selectedLocation].residents else emptyList()

        residentsUrl.forEach { url ->
            ids += url.split("/").last() + ","
        }
        return  ids
    }
}
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

    init {


        getLocations()
        getResidents()
    }

    private fun getLocations() {


        viewModelScope.launch {

            getLocationUseCase.invoke().collect { resource ->

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
            delay(2000L)

            var ids = ""
            val residentsUrl =
                if (locationList.isNotEmpty()) locationList[selectedLocation].residents else emptyList()
            println("$residentsUrl asdfkadsfsdaşfadkfajfdşka")
            residentsUrl.forEach { url ->
                ids += url.split("/").last() + ","
            }
            getResidentsUseCase(ids).collect { resource ->
                when (resource) {

                    is Resource.Loading -> {
                        println("---------Loading--------------")
                    }
                    is Resource.Error -> {
                        println("---------Error${resource.message}--------------")
                    }
                    is Resource.Success -> {
                        println("---------Success ${resource.data?.size}--------------")
                        loading = false
                        residentList = resource.data ?: emptyList()

                    }
                }
            }
        }

    }


    fun updateSelectedLocation(id : Int){

        if (id != selectedLocation){
            selectedLocation = id
            getResidents()
        }
    }
}
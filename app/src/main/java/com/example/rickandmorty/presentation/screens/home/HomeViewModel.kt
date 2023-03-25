package com.example.rickandmorty.presentation.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.use_cases.GetLocationUseCase
import com.example.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.rickandmorty.domain.model.Location


@HiltViewModel
class HomeViewModel @Inject constructor(private val getLocationUseCase: GetLocationUseCase) :
    ViewModel() {


    var locationList by mutableStateOf<List<Location>>(emptyList())
        private set

    init {
        getAll()
    }

    private fun getAll() {


        getLocationUseCase.invoke().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    println("---------Loading--------------")
                }
                is Resource.Error -> {
                    println("---------Error--------------")
                }
                is Resource.Success -> {
                    println("---------Success ${result.data?.size}--------------")
                    locationList = result.data ?: emptyList()

                }
            }
        }.launchIn(viewModelScope)


    }


}
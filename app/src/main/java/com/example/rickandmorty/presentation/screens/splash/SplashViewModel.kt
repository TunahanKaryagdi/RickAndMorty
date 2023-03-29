package com.example.rickandmorty.presentation.screens.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.util.Constants.FIRST_TIME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(sharedPreferences: SharedPreferences) : ViewModel() {


    var firstTime: Boolean? = null

    init {

        firstTime = sharedPreferences.getBoolean(FIRST_TIME_KEY,true)
        if (firstTime == true){
            val editor = sharedPreferences.edit()
            editor.putBoolean(FIRST_TIME_KEY,false)
            editor.apply()
        }

    }



}
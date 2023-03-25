package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.presentation.navigation.Navigation
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            RickAndMortyTheme {

                val navController  = rememberNavController()
                Navigation(navController = navController)

            }
        }


    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {

    }
}
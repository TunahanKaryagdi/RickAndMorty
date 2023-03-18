package com.example.rickandmorty.presentation.screens.splash

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.navigation.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {


    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,

                )
        )
        delay(2000)
        navController.navigate(NavigationItem.HomeScreen.route) {
            popUpTo(NavigationItem.SplashScreen.route) {
                inclusive = true
            }
        }

    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.twenty))

    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_logo),
            contentDescription = stringResource(
                id = R.string.logo
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.ten)))
        Text(text = stringResource(id = R.string.welcome), style = MaterialTheme.typography.h1)
    }
}
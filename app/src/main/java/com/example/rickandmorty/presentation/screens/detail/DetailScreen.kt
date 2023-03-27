package com.example.rickandmorty.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.Resident

@Composable
fun DetailScreen(
    navController: NavController,
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.twenty))
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.ten))
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {

                        navController.popBackStack()
                    }
            )
            Text(
                text = "BethSmith",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(
                id = R.string.character_logo
            ),
            modifier = Modifier
                .size(275.dp)
                .padding(
                    vertical = dimensionResource(id = R.dimen.twenty),
                    horizontal = dimensionResource(id = R.dimen.thirty)
                )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty)))

        CustomRow(key = "Status:", value = "Alive")
        CustomRow(key = "Specy:", value = "Human")
        CustomRow(key = "Gender:", value = "Female")
        CustomRow(key = "Origin:", value = "Human")
        CustomRow(key = "Location:", value = "Human")
        CustomRow(key = "Episodes:", value = "Human")
        CustomRow(key = "Created at(in API):", value = "Human")


    }

}

@Composable
fun CustomRow(
    key: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = key, style = MaterialTheme.typography.h1, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five)))
        Text(text = value, style = MaterialTheme.typography.body1, modifier = Modifier.weight(2f))
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.five)))
}

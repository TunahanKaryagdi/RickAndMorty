package com.example.rickandmorty.presentation.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.R

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()

) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.twenty))
    ) {


        if (!viewModel.loading){
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
                    text = viewModel.residentDetail?.name ?: stringResource(id = R.string.unnamed),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            AsyncImage(
                model = viewModel.residentDetail?.image ?:"",
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

            CustomRow(key = "Status:", value = viewModel.residentDetail?.status ?: "Unknown")
            CustomRow(key = "Specy:", value = viewModel.residentDetail?.specy ?: "Unknown")
            CustomRow(key = "Gender:", value = viewModel.residentDetail?.gender ?: "Unknown")
            CustomRow(key = "Origin:", value = viewModel.residentDetail?.origin ?: "Unknown")
            CustomRow(key = "Location:", value = viewModel.residentDetail?.location ?: "Unknown")
            CustomRow(key = "Episodes:", value = viewModel.fetchEpisodeFromUrl(viewModel.residentDetail?.episodes ?: emptyList()))
            CustomRow(key = "Created at(in API):", value = viewModel.residentDetail?.createdAt ?: "Unknown")
        }
        else{
            CircularProgressIndicator(color = MaterialTheme.colors.secondary,)
        }


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

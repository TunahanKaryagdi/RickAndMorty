package com.example.rickandmorty.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.Resident
import com.example.rickandmorty.presentation.navigation.NavigationItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.ten))
    ) {

        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_logo),
            contentDescription = stringResource(
                id = R.string.logo
            ),
            modifier = Modifier
                .width(150.dp)
                .background(Color.Transparent)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty)))
        LazyRow {

            items(viewModel.locationList.size) {
                CustomTextButton(
                    text = if (viewModel.locationList.isEmpty()) "" else viewModel.locationList[it].name,
                    onClick = { viewModel.updateSelectedLocation(it)  },
                    isSelected = it == viewModel.selectedLocation
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.ten)))

            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty)))

        if(!viewModel.loading){
            LazyColumn {
                items(viewModel.residentList.size) { count ->
                    if (count % 2 == 0) {
                        CustomLeftBasedCard(
                            resident = viewModel.residentList[count]
                        ) {
                            navController.navigate(NavigationItem.DetailScreen.route+"${viewModel.residentList[count].id}")
                        }
                    } else {
                        CustomRightBasedCard(
                            resident = viewModel.residentList[count]

                        ) {
                            navController.navigate(NavigationItem.DetailScreen.route+"${viewModel.residentList[count].id}")
                        }
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.ten)))


                }
            }
        }
        else{
            CircularProgressIndicator(color = MaterialTheme.colors.secondary)
        }

    }
}


@Composable
fun CustomTextButton(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    TextButton(
        onClick = onClick,
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Transparent else Color.Gray
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}


@Composable
fun CustomLeftBasedCard(

    resident: Resident,
    onClick: () -> Unit
) {

    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onClick()
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = resident.imageUrl,
                contentDescription = stringResource(
                    id = R.string.character_logo, resident.name
                ),
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
            )
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.ten))
                    .weight(5f)
            ) {
                Image(
                    modifier = Modifier.fillMaxHeight(),
                    painter = painterResource(id = if (resident.gender == "Male") R.drawable.ic_male else if (resident.gender == "Female") R.drawable.ic_female else R.drawable.ic_question_mark),
                    contentDescription = stringResource(
                        id = R.string.gender
                    )
                )
                Text(
                    text = resident.name,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

        }

    }
}

@Composable
fun CustomRightBasedCard(
    resident: Resident,
    onClick: () -> Unit
) {

    Card(
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onClick()
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.ten))
                    .weight(5f)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    painter = painterResource(id = if (resident.gender == "male") R.drawable.ic_male else if (resident.gender == "female") R.drawable.ic_female else R.drawable.ic_question_mark),
                    contentDescription = stringResource(
                        id = R.string.gender
                    )
                )
                Text(text = resident.name, style = MaterialTheme.typography.h1)
            }

            AsyncImage(
                modifier = Modifier
                    .weight(2f),
                model = resident.imageUrl,
                contentDescription = stringResource(
                    id = R.string.character_logo, resident.name
                )
            )
        }
    }
}
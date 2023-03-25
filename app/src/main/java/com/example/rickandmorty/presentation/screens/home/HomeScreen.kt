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
import com.example.rickandmorty.R
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

            items(20) {
                CustomTextButton(text = "Abadango", {}, it == 0)
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.ten)))

            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.twenty)))

        LazyColumn {
            items(5) { count ->
                if (count % 2 == 0) {
                    CustomLeftBasedCard(
                        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                        name = "Beth Smith",
                        gender = "female"
                    ) {
                        navController.navigate(NavigationItem.DetailScreen.route)
                    }
                } else {
                    CustomRightBasedCard(
                        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                        name = "Bill",
                        gender = "male"
                    ){
                        navController.navigate(NavigationItem.DetailScreen.route)
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.ten)))


            }
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
    imageUrl: String,
    name: String,
    gender: String,
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
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(
                    id = R.string.character_logo, name
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
                    painter = painterResource(id = if (gender == "male") R.drawable.ic_male else if (gender == "female") R.drawable.ic_female else R.drawable.ic_question_mark),
                    contentDescription = stringResource(
                        id = R.string.gender
                    )
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

        }

    }
}

@Composable
fun CustomRightBasedCard(
    imageUrl: String,
    name: String,
    gender: String,
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
                    painter = painterResource(id = if (gender == "male") R.drawable.ic_male else if (gender == "female") R.drawable.ic_female else R.drawable.ic_question_mark),
                    contentDescription = stringResource(
                        id = R.string.gender
                    )
                )
                Text(text = name, style = MaterialTheme.typography.h1)
            }
            Image(
                modifier = Modifier
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(
                    id = R.string.character_logo, name
                )
            )

        }
    }
}
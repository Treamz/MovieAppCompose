package com.example.movieappcompose.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappcompose.MainViewModel
import com.example.movieappcompose.data.models.Movies
import com.example.movieappcompose.navigation.Screens

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(20.dp)) {
            items(allMovies.take(10)) {
                ListItem(items = it,navController = navController)
            }
        }
    }
}

@Composable
fun ListItem(items: Movies,navController: NavController) {
    Card(modifier = Modifier
        .padding(top = 8.dp)
        .clickable {navController.navigate(Screens.Details.route + "/${items.id}") }, elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            AsyncImage(
                model = items.image.medium,
                contentDescription = "",
                modifier = Modifier.size(128.dp)
            )
            Column() {
                Text(items.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row() {
                    Text("Rating: ", fontWeight = FontWeight.Bold)
                    Text(text = items.rating.average.toString())
                }
                Row() {
                    Text("Genres: ", fontWeight = FontWeight.Bold)
                    items.genres.take(2).forEach(){
                        Text(text = "$it")
                    }
                }
                Row() {
                    Text(text = "Premiered: ", fontWeight = FontWeight.Bold)
                    Text(text = items.premiered)

                }

            }
        }
    }
}
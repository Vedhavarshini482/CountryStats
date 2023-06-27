package com.example.countrystats.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.countrystats.ConnectivityObserver
import com.example.countrystats.data.remote.models.CountryDetails
import com.example.countrystats.ui.vm.CountryViewModel

@Composable
fun DetailScreen(
    id: String,
    countryViewModel: CountryViewModel,
    navController: NavController,
    networkStatus: ConnectivityObserver.Status
) {
    val country by remember { mutableStateOf<CountryDetails>(countryViewModel.countryList[id.toInt()]) }
    val context = LocalContext.current

    if (networkStatus == ConnectivityObserver.Status.Available) {
        LazyColumn {
            item {
                Column {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = {
                                    navController.popBackStack()
                            }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                            }
                        },
                        title = {
                            Text(text = country.name.common, fontWeight = FontWeight.Bold)
                        }
                    )
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = country.flags.png,
                            imageLoader = ImageLoader.Builder(context).crossfade(true).build(),
                            contentScale = ContentScale.Fit
                        ), contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Divider()
                    Text(
                        text = "Official Name : ${country.name.official}",
                        modifier = Modifier.padding(10.dp)
                    )
                    Divider()
                    Text(
                        text = "Capital Name : ${country.capital[0]}",
                        modifier = Modifier.padding(10.dp)
                    )
                    Divider()
                    Text(text = "Total Area : ${country.area} km", modifier = Modifier.padding(10.dp))
                    Divider()
                    Text(
                        text = "Total Population : ${country.population}\n",
                        modifier = Modifier.padding(10.dp)
                    )
                    Divider()
                    Text(
                        text = "Country Description :\n ${country.flags.alt}",
                        modifier = Modifier.padding(10.dp)
                    )

                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No Internet", textAlign = TextAlign.Center)
        }
    }
}
package com.example.countrystats.ui.screens


import com.example.countrystats.data.remote.models.CountryDetails
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.countrystats.ConnectivityObserver
import com.example.countrystats.ui.nav.Screens
import com.example.countrystats.ui.vm.CountryViewModel

@Composable
fun HomeScreen(
    countryViewModel: CountryViewModel,
    navController: NavController,
    networkStatus: ConnectivityObserver.Status
) {

    val countries = countryViewModel.countryList!!

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "COUNTRY STATS", fontWeight = FontWeight.Bold)
                }
            )
        },
            floatingActionButton = {
                if(networkStatus==ConnectivityObserver.Status.Available) {
                ExtendedFloatingActionButton(
                    text = { Text("View Chart") },
                    icon = { Icon(Icons.Filled.List, contentDescription = null) },
                    onClick = {
                        navController.navigate(Screens.ViewChart.route)
                    }
                )
            }
        }
    ) {
        LaunchedEffect(key1 = networkStatus) {
            if (networkStatus == ConnectivityObserver.Status.Available) {
                countryViewModel.getCountryList()
            }
        }
        if (networkStatus == ConnectivityObserver.Status.Available) {
            if (countries.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.padding(it)) {
                        itemsIndexed(countries) { index, country ->
                            CountryCard(
                                country = country,
                                countryIndex = index,
                                navController = navController
                            )
                        }
                    }
                }
        } else {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text("No Internet")
            }
        }
    }
}

@Composable
fun CountryCard(
    country: CountryDetails,
    countryIndex: Int,
    navController: NavController
) {
    val context = LocalContext.current
    Card(
        elevation = 7.dp,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)
            .clickable {
                navController.navigate(Screens.Detail.route + "/$countryIndex")
            }
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    model = country.flags.png,
                    imageLoader = ImageLoader.Builder(context).crossfade(true).build(),
                    contentScale = ContentScale.Fit,

                ),
                contentDescription = "Flag of /${country.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(175.dp)
                    .height(115.dp)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = country.name.common, fontWeight = FontWeight.Bold)
                Text(text = country.name.official, fontWeight = FontWeight.Medium)
                Text(
                    text = country.population.toString(),
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
package com.example.countrystats.ui.screens

import com.example.countrystats.data.remote.models.CountryDetails
import androidx.compose.foundation.Image
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.countrystats.ConnectivityObserver
import com.example.countrystats.ui.nav.Screens
import com.example.countrystats.ui.vm.CountryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(
    countryViewModel: CountryViewModel,
    navController: NavController,
    networkStatus: ConnectivityObserver.Status
) {

    var isLoading by remember { mutableStateOf(true) }

    val countries = rememberListData()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "COUNTRY STATS", fontWeight = FontWeight.Bold)
                }
            )
        },
        floatingActionButton = {
            if (networkStatus == ConnectivityObserver.Status.Available) {
                ExtendedFloatingActionButton(
                    text = { Text("Top 10") },
                    icon = { Icon(Icons.Filled.List, contentDescription = null) },
                    onClick = {
                        navController.navigate(Screens.ViewChart.route)
                    }
                )
            }
        }
    ) {

        LaunchedEffect(Unit) {

            withContext(Dispatchers.IO) {
                delay(2000)
                countries.clear()
                countries.addAll(countryViewModel.countryList)
                isLoading = false
            }
        }
        LaunchedEffect(key1 = networkStatus) {
            if (networkStatus == ConnectivityObserver.Status.Available) {
                countryViewModel.getCountryList()
            }
        }

        if (isLoading)
            LoadingScreen()
        else {
            if (networkStatus == ConnectivityObserver.Status.Available) {
                if (countries.isNotEmpty()) {
                    isLoading = false
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
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = country.flags.png,
                        imageLoader = ImageLoader.Builder(context).crossfade(true).build(),
                        contentScale = ContentScale.Fit,
                    ),
                    contentDescription = "Flag of /${country.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(40.dp)
                )
                Text(
                    text = country.name.common,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Official Name : ${country.name.official}",
                modifier = Modifier.padding(5.dp),
            )
            Text(
                text = "Total Population : ${country.population}\n",
                modifier = Modifier.padding(5.dp),
            )
        }
    }
}
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
@Composable
fun rememberListData():MutableList<CountryDetails>{
    return remember{ mutableListOf() }
}
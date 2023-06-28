package com.example.countrystats.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.countrystats.ConnectivityObserver
import com.example.countrystats.MainActivity2
import com.example.countrystats.data.remote.models.CountryDetails
import com.example.countrystats.ui.vm.CountryViewModel

@Composable
fun ViewChartScreen(
    countryViewModel: CountryViewModel,
    navController: NavController,
    networkStatus: ConnectivityObserver.Status
) {
    var countries = countryViewModel.countryList!!
    var context = LocalContext.current

    Scaffold(

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "View Chart", fontWeight = FontWeight.Bold)
                }
            )
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
                    items(countries.sortedByDescending { it.population }.take(10)) { country ->
                        val names = countries.map { it.name.common }
                        val populations = countries.map { it.population }
                        CountryCard(
                            country = country
                        )
                        val intent:Intent = Intent(context, MainActivity2::class.java)
                        intent.putStringArrayListExtra("list1", ArrayList(names))
                        intent.putExtra("list2", populations.toLongArray())
                        context.startActivity(intent)
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
    country: CountryDetails
) {
    val context = LocalContext.current

    Card(
        elevation = 7.dp,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    model = country.flags.png,
                    imageLoader = ImageLoader.Builder(context).crossfade(true).build(),
                    contentScale = ContentScale.Fit
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

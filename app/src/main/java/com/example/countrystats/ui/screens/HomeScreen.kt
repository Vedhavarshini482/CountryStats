package com.example.countrystats.ui.screens

import com.example.countrystats.data.remote.models.CountryDetails
import com.example.countrystats.util.Constants.Companion.id
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.countrystats.ui.nav.Screens
import com.example.countrystats.ui.vm.CountryViewModel



@Composable
fun HomeScreen(countryViewModel: CountryViewModel, navController: NavController) {

    val countries = countryViewModel.countryList

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "COUNTRY STATS", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(countries) { country ->
                GameCard(country = country, countries = countries, navController = navController)
            }
        }
    }

}

@Composable
fun GameCard(
    country: CountryDetails,
    countries: List<CountryDetails>,
    navController: NavController
) {
    card(
        elevation = 7.dp,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)
            .clickable {
                id=countries.indexOf(country)
                navController.navigate(Screens.Detail.route + "/${countries}")
            }
    ) {
        Row {
            Image(
                painter = rememberImagePainter(country.flag.svg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(175.dp)
                    .height(115.dp)
            )
            column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)) {
                Text(text = country.name.common, fontWeight = FontWeight.Bold,contentDescription=null)
                Text(text = country.name.official, fontWeight = FontWeight.Medium,contentDescription=null)
                Text(text = country.population, fontWeight = FontWeight.Medium, overflow = TextOverflow.Ellipsis,contentDescription=null)
            }

        }
    }
}
package com.example.countrystats.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.countrystats.ui.vm.CountryViewModel


@Composable
fun DetailScreen(id:Int,countryViewModel: CountryViewModel, navController: NavController) {
    val country = countryViewModel.countryList[id]
    LazyColumn {
        item {
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    title = {
                        Text(text = "Country : /${country.name.common}", fontWeight = FontWeight.Bold)
                    }
                )
                Image(painter = rememberImagePainter(country.flag.svg), contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp))
                Text(text = "Official : /${country.name.official}\n", modifier = Modifier.padding(10.dp))
                Text(text = "Capital : /${country.capital}", modifier = Modifier.padding(10.dp))
                Text(text = "Area : /${country.area}\n", modifier = Modifier.padding(10.dp))
                Text(text = "Population : /${country.population}\n", modifier = Modifier.padding(10.dp))
                Text(text = "Country Description :\n /${country.flag.alt}", modifier = Modifier.padding(10.dp))

            }
        }
    }

}


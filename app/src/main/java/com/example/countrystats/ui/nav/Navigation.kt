package com.example.countrystats.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countrystats.ui.screens.DetailScreen
import com.example.countrystats.ui.screens.HomeScreen
import com.example.countrystats.ui.vm.CountryViewModel
import com.example.countrystats.util.Constants.Screens.DETAIL_SCREEN
import com.example.countrystats.util.Constants.Screens.HOME_SCREEN

sealed class Screens(val route: String) {

    object Home : Screens(route = HOME_SCREEN)
    object Detail : Screens(route = DETAIL_SCREEN)

}

@Composable
fun SetupNavHost(countryViewModel: CountryViewModel, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(countryViewModel = countryViewModel, navController = navController)
        }
        composable(route = Screens.Detail.route) { backStackEntry ->
            DetailScreen(
                id = id,
                countryViewModel = countryViewModel,
                navController = navController
            )
        }
    }
}
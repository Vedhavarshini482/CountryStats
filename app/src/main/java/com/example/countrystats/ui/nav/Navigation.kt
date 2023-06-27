package com.example.countrystats.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.example.countrystats.ConnectivityObserver
import com.example.countrystats.ui.screens.DetailScreen
import com.example.countrystats.ui.screens.HomeScreen
import com.example.countrystats.ui.screens.ViewChartScreen
import com.example.countrystats.ui.vm.CountryViewModel
import com.example.countrystats.util.Constants.Screens.DETAIL_SCREEN
import com.example.countrystats.util.Constants.Screens.HOME_SCREEN
import com.example.countrystats.util.Constants.Screens.VIEWCHART_SCREEN

sealed class Screens(val route: String){
    object Home : Screens(route = HOME_SCREEN)
    object Detail : Screens(route = DETAIL_SCREEN)

    object ViewChart : Screens(route= VIEWCHART_SCREEN)
}

@Composable
fun SetupNavHost(
    countryViewModel: CountryViewModel,
    navController: NavHostController,
    networkStatus: ConnectivityObserver.Status
) {

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                countryViewModel = countryViewModel,
                navController = navController,
                networkStatus = networkStatus
            )
        }
        composable(route = Screens.Detail.route + "/{countryId}") { navBackStackEntry->

            DetailScreen(
                id = navBackStackEntry.arguments?.getString("countryId")!!,
                countryViewModel = countryViewModel,
                navController = navController,
                networkStatus = networkStatus
            )
        }

        composable(route=Screens.ViewChart.route){
         ViewChartScreen(
             countryViewModel=countryViewModel,
             navController = navController,
             networkStatus = networkStatus
         )
        }
    }
}


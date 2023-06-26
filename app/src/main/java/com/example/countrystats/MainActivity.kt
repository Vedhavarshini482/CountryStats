package com.example.countrystats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.countrystats.ui.nav.SetupNavHost
import com.example.countrystats.ui.theme.CountryStatsTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryStatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    SetupNavHost(navController = navController, countryViewModel = viewModel())
                }
            }
        }
    }
}

//@Composable
//fun BarChart(
//    inputList: List<BarChartInput>,
//    modifier: Modifier=Modifier,
//    showDescription:Boolean
//){
//
//    Row(
//        modifier=Modifier,
//        verticalAlignment=Alignment.Bottom,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ){
//        val listSum by remember{
//            mutableStateOf(inputList.sumOf { it.value })
//        }
//        inputList.forEach{input->
//            val percentage=input.value/listSum.toFloat()
//            Bar(modifier=Modifier
//                .height(120.dp*percentage*inputList.size)
//                .width(40.dp),
//                primaryColor = input.color,
//                percentage=percentage,
//                description = input.Description,
//                showDescription=showDescription
//                )
//        }
//    }
//}
//
//@Composable
//fun Bar(
//    modifier:Modifier=Modifier,
//    primaryColor:Color,
//    percentage:Float,
//    description:String,
//    showDescription: Boolean
//){}
//
//data class BarChartInput(
//    val value:Int,
//    val Description:String,
//    var color:Color
//)
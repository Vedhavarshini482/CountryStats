package com.example.countrystats.util

import com.github.mikephil.charting.charts.BarChart

class Constants {
    companion object {
        const val BASE_URL = "https://restcountries.com/"
        const val COUNTRY_ENDPOINT =
            "v3.1/independent?status=true&fields=name,capital,currencies,population,area,flags"
    }

object Screens {
    const val HOME_SCREEN = "home_screen"
    const val DETAIL_SCREEN = "detail_screen"
    const val VIEWCHART_SCREEN="viewChart_screen"
}
}

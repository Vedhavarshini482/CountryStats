package com.example.countrystats.data.remote.models


data class CountryDetails(
    var flags:Flag,
    var name: Name,
    var capital: List<String>,
    var area: Float,
    var population: Long
)
//fun CountryDetails.toCountryDetails()= CountryDetails(flag, name, capital, area, population)

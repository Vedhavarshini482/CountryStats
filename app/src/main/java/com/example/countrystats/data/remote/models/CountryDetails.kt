package com.example.countrystats.data.remote.models


data class CountryDetails(
var flag:Flag,
var name: Name,
var capital: Capital,
var area: Long,
var population: Long
)
//fun CountryDetails.toCountryDetails()= CountryDetails(flag, name, capital, area, population)

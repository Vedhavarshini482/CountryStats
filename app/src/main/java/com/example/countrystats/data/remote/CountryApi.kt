package com.example.countrystats.data.remote

import com.example.countrystats.data.remote.models.CountryDetails
import com.example.countrystats.util.Constants.Companion.BASE_URL
import com.example.countrystats.util.Constants.Companion.COUNTRY_ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryApi {
    @GET(COUNTRY_ENDPOINT)
    suspend fun getCountryDetails(): List<CountryDetails>

    companion object {
        var apiService: CountryApi? = null
        fun getInstance(): CountryApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CountryApi::class.java)

            }
            return apiService!!
        }
    }
}

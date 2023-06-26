package com.example.countrystats.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrystats.data.remote.CountryApi
import com.example.countrystats.data.remote.models.CountryDetails
import kotlinx.coroutines.launch

class CountryViewModel:ViewModel(){

    private val _countryList= mutableStateListOf<CountryDetails>()
    var errorMessage:String by mutableStateOf("")
    val countryList: List<CountryDetails>
        get()=_countryList

    init {
        getCountryList()
    }
     fun getCountryList(){
        viewModelScope.launch {
            val countryApi=CountryApi.getInstance()
            try{
                _countryList.clear()
                _countryList.addAll(countryApi.getCountryDetails())
            }catch (e:Exception){
                errorMessage=e.message.toString()
            }
        }
    }
}
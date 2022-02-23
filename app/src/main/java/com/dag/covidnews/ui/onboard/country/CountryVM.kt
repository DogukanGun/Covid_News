package com.dag.covidnews.ui.onboard.country

import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryVM @Inject constructor(val apiSource: ApiSource) :CovidVM(){

    fun getCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiSource.getAllCountries()
            if (response.isSuccessful){
                var list = response.body()
                if (list!=null){
                    var newList = list.response?.filter { country->
                        Country.values().map { it.name.lowercase() }.contains(country.lowercase())
                    }
                    newList?.let {
                        state.postValue(CountryVS.SetCountries(it))
                    }
                }
            }
        }
    }

    fun nextButtonPressed(){
        state.postValue(CountryVS.SortList)
    }
}
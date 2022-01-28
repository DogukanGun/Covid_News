package com.dag.covidnews.ui.homepage.viewpager

import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewPagerVM @Inject constructor(val apiSource: ApiSource) :CovidVM() {

    fun getList(countryName: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiSource.getCountryInformation(countryName.lowercase())
            if (response.isSuccessful){
                response.body()?.let {
                    state.postValue(ViewPagerVS.SetViewPagerValues(it))
                }
            }
        }
    }

}
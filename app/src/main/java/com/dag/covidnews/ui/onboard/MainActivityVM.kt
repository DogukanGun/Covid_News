package com.dag.covidnews.ui.onboard

import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityVM @Inject constructor(val apiSource: ApiSource):CovidVM() {

    fun getAllCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiSource.getAllCountries()
            if (response.isSuccessful){
                val list = response.body()
                print(list)
            }
        }
    }
}
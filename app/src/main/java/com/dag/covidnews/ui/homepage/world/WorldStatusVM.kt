package com.dag.covidnews.ui.homepage.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorldStatusVM @Inject constructor(val apiSource: ApiSource):CovidVM() {

    fun getWorldData(selectedCountry:String?){
        viewModelScope.launch(Dispatchers.IO) {
            selectedCountry?.let {
                if (it.isNotBlank()){
                    val data = apiSource.getWorldData(it)
                    if (data.isSuccessful){
                        val body = data.body()
                        body?.let {
                            it.response?.get(0)?.let {
                                state.postValue(WorldStatusVS.SetWordStatus(it))
                            }
                        }
                    }
                }
            }
        }
    }
}
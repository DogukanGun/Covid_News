package com.dag.covidnews.ui.homepage.world

import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorldStatusVM @Inject constructor(val apiSource: ApiSource):CovidVM() {


    fun getWorldData(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = apiSource.getWorldData()
            if (data.isSuccessful){
                val body = data.body()
                body?.get(0)?.let {
                    state.postValue(WorldStatusVS.SetWordStatus(it))
                }
            }
        }
    }
}
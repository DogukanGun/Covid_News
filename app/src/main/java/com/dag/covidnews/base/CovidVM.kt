package com.dag.covidnews.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dag.covidnews.network.ApiServiceImpl


abstract class CovidVM :ViewModel() {
    val state = MutableLiveData<CovidState>()

}
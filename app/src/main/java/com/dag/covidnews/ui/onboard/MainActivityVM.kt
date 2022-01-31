package com.dag.covidnews.ui.onboard

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.dag.covidnews.base.AppConstant
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.network.ApiSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityVM @Inject constructor(val context: Context):CovidVM() {

    fun getCountries(){
        val prefs = context.getSharedPreferences(AppConstant.PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val countries = prefs.getString(AppConstant.PREFS_COUNTRY_KEY,null)
        if (countries != null){
            state.postValue(MainActivityVS.StartActivity)
        }else{
            state.postValue(MainActivityVS.StoryMode)
        }
    }
}
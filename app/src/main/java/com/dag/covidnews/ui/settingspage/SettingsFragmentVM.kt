package com.dag.covidnews.ui.settingspage

import android.content.Context
import com.dag.covidnews.R
import com.dag.covidnews.base.AppConstant
import com.dag.covidnews.base.CovidVM
import com.dag.covidnews.entity.country.Country
import com.dag.covidnews.ui.homepage.HomepageFragmentVS
import javax.inject.Inject


class SettingsFragmentVM @Inject constructor(val context: Context):CovidVM() {

    fun getList(){
        val list: MutableList<Country> = mutableListOf()
        val prefs = context.getSharedPreferences(AppConstant.PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val countries = prefs?.getString(AppConstant.PREFS_COUNTRY_KEY,null)
        for (index in countries!!.split(",")){
            val newIndex = index.replace(" ","")
            val country = Country.values().filter {
                it.id == newIndex.toInt()
            }
            list.add(country[0])
        }

        state.postValue(SettingsFragmentVS.GetCountries(list))
    }
}
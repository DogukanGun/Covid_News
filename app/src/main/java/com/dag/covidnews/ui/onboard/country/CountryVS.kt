package com.dag.covidnews.ui.onboard.country

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.CountryEntity

sealed class CountryVS:CovidState {
    class SetCountries(val list: List<String>):CountryVS()
    object SortList:CountryVS()
}
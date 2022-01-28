package com.dag.covidnews.ui.homepage.viewpager

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.CountryInformation
import com.dag.covidnews.entity.country.GetCountryInformation

sealed class ViewPagerVS: CovidState {

    class SetViewPagerValues(val response:List<CountryInformation>):ViewPagerVS()
}
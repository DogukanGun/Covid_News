package com.dag.covidnews.ui.homepage.viewpager

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.CountryInformation

sealed class ViewPagerVS: CovidState {

    class SetViewPagerValues(val response:CountryInformation):ViewPagerVS()
}
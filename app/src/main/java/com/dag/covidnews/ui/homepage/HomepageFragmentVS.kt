package com.dag.covidnews.ui.homepage

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.Country

sealed class HomepageFragmentVS:CovidState {
    class GetListFromPrefs(var list: List<Country>):HomepageFragmentVS()
}
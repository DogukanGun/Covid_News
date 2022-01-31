package com.dag.covidnews.ui.settingspage

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.Country

sealed class SettingsFragmentVS:CovidState {
    class GetCountries(val list: List<Country>):SettingsFragmentVS()
}
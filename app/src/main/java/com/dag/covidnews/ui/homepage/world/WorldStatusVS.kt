package com.dag.covidnews.ui.homepage.world

import com.dag.covidnews.base.CovidState
import com.dag.covidnews.entity.country.CountryInformation

sealed class WorldStatusVS :CovidState{
    class SetWordStatus(val countryInformation: CountryInformation):WorldStatusVS()
}
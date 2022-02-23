package com.dag.covidnews.ui.onboard.country

import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryWrapper

interface CountryListener {
    fun itemClicked(item: CountryWrapper)
}
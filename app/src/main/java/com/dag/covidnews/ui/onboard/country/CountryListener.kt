package com.dag.covidnews.ui.onboard.country

import com.dag.covidnews.entity.country.CountryEntity

interface CountryListener {
    fun itemClicked(item:CountryEntity)
}
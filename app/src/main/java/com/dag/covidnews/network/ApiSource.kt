package com.dag.covidnews.network

import com.dag.covidnews.entity.country.CountryEntity
import retrofit2.Response

interface ApiSource {

    suspend fun getAllCountries():Response<List<CountryEntity>>
}
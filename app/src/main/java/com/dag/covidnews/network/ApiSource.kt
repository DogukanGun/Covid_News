package com.dag.covidnews.network

import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import com.dag.covidnews.entity.country.GetCountryInformation
import retrofit2.Response

interface ApiSource {

    suspend fun getAllCountries():Response<List<CountryEntity>>

    suspend fun getCountryInformation(name: String): Response<List<CountryInformation>>
}
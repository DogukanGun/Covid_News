package com.dag.covidnews.network

import com.dag.covidnews.entity.country.AllCountry
import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import retrofit2.Response

interface ApiSource {

    suspend fun getAllCountries():Response<CountryEntity>

    suspend fun getCountryInformation(name: String): Response<AllCountry>

    suspend fun getWorldData(name: String):Response<AllCountry>

}
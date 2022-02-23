package com.dag.covidnews.network

import com.dag.covidnews.entity.country.AllCountry
import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("countries")
    suspend fun getAllCountries(): Response<CountryEntity>

    @GET("statistics")
    suspend fun getCountryInformation(@Query("country") name: String):
            Response<AllCountry>

    @GET("history")
    suspend fun getWorldData(@Query("country") name: String):Response<AllCountry>

}
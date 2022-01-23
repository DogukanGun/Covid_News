package com.dag.covidnews.network

import com.dag.covidnews.entity.country.CountryEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("help/countries")
    suspend fun getAllCountries(): Response<List<CountryEntity>>

}
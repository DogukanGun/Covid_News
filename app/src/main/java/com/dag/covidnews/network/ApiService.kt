package com.dag.covidnews.network

import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import com.dag.covidnews.entity.country.GetCountryInformation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("help/countries")
    suspend fun getAllCountries(): Response<List<CountryEntity>>

    @GET("country")
    suspend fun getCountryInformation(@Query("name") name: String):
            Response<List<CountryInformation>>

}
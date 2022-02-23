package com.dag.covidnews.network

import com.dag.covidnews.entity.country.AllCountry
import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject
constructor(private val retrofit: Retrofit):ApiSource{

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getAllCountries(): Response<CountryEntity> {
        return apiService.getAllCountries()
    }

    override suspend fun getCountryInformation(
        name: String
    ): Response<AllCountry> {
        return apiService.getCountryInformation(name)
    }

    override suspend fun getWorldData(name: String): Response<AllCountry> {
        return apiService.getWorldData(name)
    }


}
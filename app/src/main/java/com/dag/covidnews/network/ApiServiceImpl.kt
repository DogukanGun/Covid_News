package com.dag.covidnews.network

import com.dag.covidnews.entity.country.CountryEntity
import com.dag.covidnews.entity.country.CountryInformation
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject
constructor(private val retrofit: Retrofit):ApiSource{

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getAllCountries(): Response<List<CountryEntity>> {
        return apiService.getAllCountries()
    }

    override suspend fun getCountryInformation(
        name: String
    ): Response<List<CountryInformation>> {
        return apiService.getCountryInformation(name)
    }

    override suspend fun getWorldData(): Response<List<CountryInformation>> {
        return apiService.getWorldData()
    }


}
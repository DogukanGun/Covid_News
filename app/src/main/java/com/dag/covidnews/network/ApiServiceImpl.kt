package com.dag.covidnews.network

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.ViewModel
import com.dag.covidnews.entity.country.CountryEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject
constructor(private val retrofit: Retrofit):ApiSource{

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getAllCountries(): Response<List<CountryEntity>> {
        return apiService.getAllCountries()
    }

}
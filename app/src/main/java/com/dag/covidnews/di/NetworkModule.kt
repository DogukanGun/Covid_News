package com.dag.covidnews.di

import com.dag.covidnews.base.AppConstant
import com.dag.covidnews.network.ApiServiceImpl
import com.dag.covidnews.network.ApiSource
import com.dag.covidnews.retrofit.ApiLogger
import com.dag.covidnews.retrofit.HttpRetrofitInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
class NetworkModule {

   @Provides
   fun provideApiSource(retrofit: Retrofit):ApiSource = ApiServiceImpl(retrofit)

   @Provides
   fun provideRetrofit(): Retrofit {
       val logger = HttpLoggingInterceptor(ApiLogger())
       logger.level = HttpLoggingInterceptor.Level.BODY

       val interceptor = HttpRetrofitInterceptor()
       val httpClient = OkHttpClient.Builder()
           .addInterceptor(logger)
           .addInterceptor(interceptor)
           .build()
       return Retrofit.Builder()
           .baseUrl(AppConstant.url)
           .addConverterFactory(GsonConverterFactory.create())
           .client(httpClient)
           .build()
   }
}
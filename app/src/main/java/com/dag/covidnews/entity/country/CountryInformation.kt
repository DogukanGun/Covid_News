package com.dag.covidnews.entity.country

data class CountryInformation(val country: String,
                              val code: String,
                              val confirmed: Int?,
                              val recovered: Int?,
                              val deaths: Int?,
                              val latitude: Double?,
                              val longitude: Double?,
                              val lastChange: String?,
                              val lastUpdate: String?)

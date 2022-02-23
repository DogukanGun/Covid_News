package com.dag.covidnews.entity.country

data class CountryInformation(val country: String,
                              val cases:Cases,
                              val deaths:Cases,
                              val tests:Cases
)

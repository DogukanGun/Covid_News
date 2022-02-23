package com.dag.covidnews.entity.country

data class AllCountry(   var get:String = "",
                         var results:Int = 0,
                         var response:List<CountryInformation>? = null)
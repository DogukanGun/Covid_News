package com.dag.covidnews.entity.country

data class CountryEntity (
    var name:String? = "",
    var alpha2code:String? = "",
    var alpha3code:String? = "",
    var isSelected:Int = 0,
)
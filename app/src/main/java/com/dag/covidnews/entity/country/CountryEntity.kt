package com.dag.covidnews.entity.country

data class CountryEntity (
    var get:String = "",
    var results:Int = 0,
    var response:List<String>? = null,
    var isSelected:Int = 0,
)
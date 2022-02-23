package com.dag.covidnews.entity.country

data class Cases(val new: String,
                 val active:Int,
                 val critical:Int,
                 val recovered:Int,
                 val total: Int)

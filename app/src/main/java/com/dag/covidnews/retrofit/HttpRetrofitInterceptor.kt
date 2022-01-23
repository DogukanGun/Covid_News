package com.dag.covidnews.retrofit

import com.dag.covidnews.base.AppConstant
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer

class HttpRetrofitInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestWithUrl = request.newBuilder()
            .url(request.url.toString())
            .build()
        return chain.proceed(createRequest(requestWithUrl))
    }

    private fun createRequest(request: Request):Request{
        val buffer = Buffer()
        request.body?.writeTo(buffer)
        return request.newBuilder()
            .header("X-RapidAPI-Host","covid-19-data.p.rapidapi.com")
            .header("X-RapidAPI-Key","b9739f67d9msh90e038c9e8ff0c3p1e239fjsne39813ae8648")
            .method(request.method,request.body)
            .build()

    }
}
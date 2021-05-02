package com.example.dell.covidtweets

import com.example.dell.covidtweets.api.NewsApiJSON
import retrofit2.http.GET

interface APIRequest {
    @GET("/v1/search?keywords=Corona&country=IN&apiKey=gnSJBtEglPL2XnrU1dGGHDQB_D8UvfTgBwuqyjrBxyvws_nz")
    suspend fun getNews() : NewsApiJSON
}
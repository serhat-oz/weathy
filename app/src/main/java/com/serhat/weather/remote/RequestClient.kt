package com.serhat.weather.remote

import com.serhat.weather.model.searchresults.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestClient {
    @GET("locations/v1/cities/autocomplete")
    fun getAutoCompletePlace(@Query("q") value: String): Call<SearchResult>

}
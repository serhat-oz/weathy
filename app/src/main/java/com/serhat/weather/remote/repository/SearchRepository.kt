package com.serhat.weather.remote.repository

import android.util.Log
import com.serhat.weather.model.searchresults.SearchResult
import com.serhat.weather.remote.RequestFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    fun getSearchResultValues(
        username: String,
        onResult: (isSuccess: Boolean, response: List<SearchRepository>?) -> Unit
    ) {
        RequestFactory.instance.getAutoCompletePlace(username)
            .enqueue(object : Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Log.d("testfailure",t.toString())
                }

                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    Log.d("testfailure",response.toString())
                }

            })
    }

    companion object {
        private var INSTANCE: SearchRepository? = null
        fun getInstance() = INSTANCE
            ?: SearchRepository().also {
                INSTANCE = it
            }
    }
}
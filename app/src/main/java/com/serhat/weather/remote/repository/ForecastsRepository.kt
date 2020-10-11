package com.serhat.weather.remote.repository

import android.util.Log
import com.serhat.weather.model.dailyforecasts.Forecasts
import com.serhat.weather.model.searchresults.SearchResult
import com.serhat.weather.remote.RequestFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastsRepository {

    fun getForecastDatas(
        cityId: String,
        onResult: (isSuccess: Boolean, response: Forecasts?) -> Unit
    ) {
        RequestFactory.instance.getPlaceDetailForecast(cityId)
            .enqueue(object : Callback<Forecasts> {
                override fun onFailure(call: Call<Forecasts>, t: Throwable) {
                    onResult(false, null)
                }

                override fun onResponse(
                    call: Call<Forecasts>,
                    response: Response<Forecasts>
                ) {
                    if (response.code() == 200) {
                        onResult(true, response.body())
                    } else {
                        onResult(false, response.body())
                    }
                }


            })
    }

    companion object {
        private var INSTANCE: ForecastsRepository? = null
        fun getInstance() = INSTANCE
            ?: ForecastsRepository().also {
                INSTANCE = it
            }
    }
}
package com.serhat.weather.model.dailyforecasts

import java.io.Serializable

data class Forecasts(
    val DailyForecasts: List<DailyForecast>,
    val Headline: Headline
):Serializable
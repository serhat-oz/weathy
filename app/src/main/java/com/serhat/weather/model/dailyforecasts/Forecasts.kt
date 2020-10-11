package com.serhat.weather.model.dailyforecasts

data class Forecasts(
    val DailyForecasts: List<DailyForecast>,
    val Headline: Headline
)
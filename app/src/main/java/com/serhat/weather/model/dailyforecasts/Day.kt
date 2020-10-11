package com.serhat.weather.model.dailyforecasts

data class Day(
    val HasPrecipitation: Boolean,
    val Icon: Int,
    val IconPhrase: String
)
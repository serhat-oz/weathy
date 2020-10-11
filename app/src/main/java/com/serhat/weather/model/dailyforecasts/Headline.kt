package com.serhat.weather.model.dailyforecasts

data class Headline(
    val Category: String,
    val EffectiveDate: String,
    val EffectiveEpochDate: Int,
    val EndDate: Any,
    val EndEpochDate: Any,
    val Link: String,
    val MobileLink: String,
    val Severity: Int,
    val Text: String
)
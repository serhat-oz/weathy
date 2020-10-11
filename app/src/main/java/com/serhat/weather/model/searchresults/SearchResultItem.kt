package com.serhat.weather.model.searchresults

data class SearchResultItem(
    val AdministrativeArea: AdministrativeArea,
    val Country: Country,
    val Key: String,
    val LocalizedName: String,
    val Rank: Int,
    val Type: String,
    val Version: Int
)
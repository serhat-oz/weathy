package com.serhat.weather.ui.listofplaces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel:ViewModel() {
    var data = MutableLiveData<String>()

    fun data(item: String){
        data.value = item
    }
}
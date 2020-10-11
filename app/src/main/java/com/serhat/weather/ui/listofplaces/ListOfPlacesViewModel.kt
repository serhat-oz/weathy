package com.serhat.weather.ui.listofplaces

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.serhat.weather.model.searchresults.SearchResult
import com.serhat.weather.remote.repository.SearchRepository
import com.serhat.weather.ui.base.BaseViewModel

class ListOfPlacesViewModel (application: Application) : BaseViewModel(application) {

    val liveCityList = MutableLiveData<SearchResult>()
    val obsSearchKey = ObservableField<String>()

    fun fetchSearchResults (){
        if (!obsSearchKey.get().isNullOrEmpty()){
            dataLoading.value = true
            SearchRepository.getInstance().getSearchResultValues(obsSearchKey.get()!!) { isSuccess, response ->
                dataLoading.value = false
                if (isSuccess) {
                    liveCityList.value = response
                    empty.value = false
                } else {
                    empty.value = true
                    toastMessage.value = "not found"
                }
            }
        }else{
            toastMessage.value = "Please fill the city name area"
        }
    }
}
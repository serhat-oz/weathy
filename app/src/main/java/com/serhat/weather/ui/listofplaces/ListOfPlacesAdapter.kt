package com.serhat.weather.ui.listofplaces

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.serhat.weather.R
import com.serhat.weather.BR
import com.serhat.weather.databinding.SearchResultRowItemBinding
import com.serhat.weather.model.searchresults.SearchResultItem
import com.serhat.weather.remote.repository.ForecastsRepository
import com.serhat.weather.remote.repository.SearchRepository


class ListOfPlacesAdapter(private val listOfPlacesViewModel: ListOfPlacesViewModel) : RecyclerView.Adapter<ListOfPlacesViewHolder>() {
    private var searchResultList: List<SearchResultItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfPlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = SearchResultRowItemBinding.inflate(inflater, parent, false)
        return ListOfPlacesViewHolder(dataBinding, listOfPlacesViewModel)
    }

    override fun getItemCount() = searchResultList.size

    override fun onBindViewHolder(holder: ListOfPlacesViewHolder, position: Int) {
        holder.bind(searchResultList[position])
    }

    fun updatePlacesList(placesList: List<SearchResultItem>) {
        this.searchResultList = placesList
        notifyDataSetChanged()
    }

}
class ListOfPlacesViewHolder constructor(private val dataBinding: ViewDataBinding, private val listOfPlacesViewModel: ListOfPlacesViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {


    fun bind(itemData: SearchResultItem) {
        dataBinding.setVariable(BR.rowItemData, itemData)
        dataBinding.executePendingBindings()



        itemView.setOnClickListener {
            val bundle = Bundle()
            listOfPlacesViewModel.dataLoading.value = true
            ForecastsRepository.getInstance().getForecastDatas(itemData.Key) { isSuccess, response ->
                listOfPlacesViewModel.dataLoading.value = false
                if (isSuccess) {
                    bundle.putSerializable("forecasts", response)
                    itemView.findNavController().navigate(R.id.action_city_list_to_weather_detail_fragment, bundle)
                } else {
                    listOfPlacesViewModel.toastMessage.value = "not found"
                }
            }
        }
    }
}
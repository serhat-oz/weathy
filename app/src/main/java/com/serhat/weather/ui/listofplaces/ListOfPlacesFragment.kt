package com.serhat.weather.ui.listofplaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.serhat.weather.R
import com.serhat.weather.databinding.FragmentListOfPlacesBinding
import com.serhat.weather.model.searchresults.SearchResult
import kotlinx.android.synthetic.main.fragment_list_of_places.*
import kotlinx.android.synthetic.main.fragment_list_of_places.view.*


class ListOfPlacesFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentListOfPlacesBinding
    private lateinit var searchResults: SearchResult
    private lateinit var placesListAdapter: ListOfPlacesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentListOfPlacesBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ListOfPlacesFragment).get(ListOfPlacesViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initObservers()
        createAdapter()

    }

    private fun initObservers() {
        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        viewDataBinding.viewmodel?.liveCityList?.observe(viewLifecycleOwner, Observer {
            placesListAdapter.updatePlacesList(it)
        })


    }

    private fun initToolbar() {
        val toolbar = viewDataBinding.tbMain
        toolbar.tb_title_main.text = getString(R.string.app_name)
    }

    private fun createAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            placesListAdapter = ListOfPlacesAdapter(viewModel)
            val layoutManager = LinearLayoutManager(activity)
            rvSearchResult.layoutManager = layoutManager
            rvSearchResult.adapter = placesListAdapter
        }
    }


}
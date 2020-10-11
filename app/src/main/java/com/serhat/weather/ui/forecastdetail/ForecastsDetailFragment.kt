package com.serhat.weather.ui.forecastdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.serhat.weather.R
import com.serhat.weather.databinding.FragmentForecastsDetailBinding
import com.serhat.weather.model.dailyforecasts.Forecasts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_forecasts_detail.*
import kotlinx.android.synthetic.main.fragment_forecasts_detail.view.*

class ForecastsDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentForecastsDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentForecastsDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ForecastsDetailFragment).get(ForecastDetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var forecastData = arguments?.let { ForecastsDetailFragmentArgs.fromBundle(it).forecasts }
        viewDataBinding.setVariable(BR.rowItemData, forecastData)

        initViews(forecastData)

        showForecastImage(forecastData)

    }

    private fun showForecastImage(forecastData: Forecasts?) {
        if(forecastData!!.DailyForecasts[0].Day.Icon < 9){
            Picasso.get().load("https://developer.accuweather.com/sites/default/files/0"+ forecastData!!.DailyForecasts[0].Day.Icon +"-s.png").placeholder(
                R.drawable.place_holder)
                .into(ivForecastImage)
        }else{
            Picasso.get().load("https://developer.accuweather.com/sites/default/files/"+forecastData!!.DailyForecasts[0].Day.Icon+"-s.png").placeholder(
                R.drawable.place_holder)
                .into(ivForecastImage)
        }
        if(forecastData!!.DailyForecasts[0].Night.Icon < 9){
            Picasso.get().load("https://developer.accuweather.com/sites/default/files/0"+ forecastData!!.DailyForecasts[0].Night.Icon +"-s.png").placeholder(
                R.drawable.place_holder)
                .into(ivForecastImageNight)
        }else{
            Picasso.get().load("https://developer.accuweather.com/sites/default/files/"+forecastData!!.DailyForecasts[0].Night.Icon+"-s.png").placeholder(
                R.drawable.place_holder)
                .into(ivForecastImageNight)
        }

    }

    private fun initViews(forecastData: Forecasts?) {
        val dailyFahrenheit = forecastData!!.DailyForecasts[0].Temperature.Maximum.Value
        val dailyCelcius = ((dailyFahrenheit - 32) * 5) / 9
        tvTemparature.text = "$dailyCelcius celcius"
        tvTemparatureText.text = forecastData.DailyForecasts[0].Day.IconPhrase

        val nightlyFahrenheit = forecastData!!.DailyForecasts[0].Temperature.Minimum.Value
        val nightlyCelcius = ((nightlyFahrenheit - 32) * 5) / 9
        tvTemparatureNight.text = "$nightlyCelcius celcius"
        tvTemparatureTextNight.text =forecastData.DailyForecasts[0].Night.IconPhrase

        val toolbar = viewDataBinding.tbDetail
        toolbar.ivToolbarBackButton.setOnClickListener{
            activity?.onBackPressed()
        }
    }





}
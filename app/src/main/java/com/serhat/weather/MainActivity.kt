package com.serhat.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.serhat.weather.remote.repository.SearchRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SearchRepository.getInstance().getSearchResultValues("ist") { isSuccess, response ->
           Log.d("testserviceconnection",response.toString())
        }
    }
}
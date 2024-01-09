package com.example.countries

import android.app.Application
import com.example.countries.api.CountriesApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    val countriesApiService: CountriesApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(CountriesApiService::class.java)
    }

    val countriesRepository: CountryRepository by lazy {
        CountryRepository(countriesApiService)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
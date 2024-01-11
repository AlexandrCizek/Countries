package com.example.countries

import android.app.Application
import androidx.room.Room
import com.example.countries.api.CountriesApiService
import com.example.countries.roomDatabase.CountryDatabase
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

    val countryDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CountryDatabase::class.java,
            "countries.db"
        ).build()
    }

    val countriesRepository: CountryRepository by lazy {
        CountryRepository(countriesApiService, countryDatabase.countryDao())
    }
}
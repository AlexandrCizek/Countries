package com.example.countries

import androidx.lifecycle.LiveData
import com.example.countries.api.CountriesApiService
import com.example.countries.model.Country
import com.example.countries.roomDatabase.CountryDao

class CountryRepository(
    private val countriesApiService: CountriesApiService,
    private val countryDao: CountryDao
) {
    suspend fun getCountriesByName(name: String) : List<Country>? {
        val response = countriesApiService.getCountriesByName(name)

        if(response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

    suspend fun getCountriesByRegion(region: String) : List<Country>? {
        val response = countriesApiService.getCountriesByRegion(region)

        if(response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }

    suspend fun getSavedCountries(): List<Country> {
        return countryDao.getAllCountries()
    }
}
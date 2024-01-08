package com.example.countries

import com.example.countries.api.CountriesApiService
import com.example.countries.model.Country

class CountryRepository(private val countriesApiService: CountriesApiService) {
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
}
package com.example.countries.api

import com.example.countries.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {
    companion object {
        const val SEARCH_BY_NAME_ENDPOINT = "https://restcountries.com/v3.1/name"
        const val SEARCH_BY_REGION_ENDPOINT = "https://restcountries.com/v3.1/region"
    }

    @GET("$SEARCH_BY_NAME_ENDPOINT/{countryName}")
    suspend fun getCountriesByName(
        @Path("countryName") countryName: String
    ): Response<List<Country>>

    @GET("$SEARCH_BY_REGION_ENDPOINT/{countryName}")
    suspend fun getCountriesByRegion(
        @Path("countryName") countryName: String
    ): Response<List<Country>>
}
package com.example.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CountriesListViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountriesListViewModel::class.java)) {
            return CountriesListViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong View Model class")
    }
}
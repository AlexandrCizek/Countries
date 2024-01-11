package com.example.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SavedCountriesViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SavedCountriesViewModel::class.java)) {
            return SavedCountriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong View Model class")
    }
}
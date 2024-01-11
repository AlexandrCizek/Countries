package com.example.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.model.Country
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SavedCountriesViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    init {
        getSavedCountries()
    }

    private fun updateCountriesList(countries: List<Country>?) {
        countries?.let {
            val sortedCountries = countries.sortedBy { it.name.common }
            _countries.postValue(sortedCountries)
            return
        }
    }

    private fun getSavedCountries() {
        viewModelScope.launch {
            repository.getSavedCountries().collect() { countries ->
                updateCountriesList(countries)
            }
        }
    }
}
package com.example.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesListViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries : LiveData<List<Country>> = _countries

    private fun updateCountriesList(countries: List<Country>?) {
        countries?.let {
            val sortedCountries = countries.sortedBy { it.name.common }
            _countries.postValue(sortedCountries)
            return
        }
    }

    fun getCountriesByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCountriesByName(name)
            updateCountriesList(result)
        }
    }

    fun getCountriesByRegion(region: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCountriesByRegion(region)
            updateCountriesList(result)
        }
    }
}
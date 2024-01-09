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

    fun getCountriesByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCountriesByName(name)
            _countries.postValue(result)
        }
    }
}
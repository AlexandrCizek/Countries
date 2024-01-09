package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.countries.databinding.ActivityCountriesListBinding

class CountriesListActivity : AppCompatActivity() {

    private lateinit var countriesListBinding: ActivityCountriesListBinding
    private lateinit var countriesListViewModel: CountriesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)

        val appContext = application
        val app = appContext as? MyApplication ?: run {
            Log.e("CountriesListActivity", "Application context is not MyApplication")
            return
        }

        countriesListBinding = ActivityCountriesListBinding.inflate(layoutInflater)
        val view = countriesListBinding.root
        setContentView(view)

        countriesListViewModel = ViewModelProvider(this, CountriesListViewModelFactory(app.countriesRepository))
            .get(CountriesListViewModel::class.java)

        countriesListBinding.countriesListViewModel = countriesListViewModel
        countriesListBinding.lifecycleOwner = this
    }
}
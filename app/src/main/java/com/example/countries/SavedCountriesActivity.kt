package com.example.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.countries.databinding.ActivitySavedCountriesBinding

class SavedCountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedCountriesBinding
    private lateinit var viewModel: SavedCountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContext = application
        val app = appContext as? MyApplication ?: run {
            Log.e("CountriesListActivity", "Application context is not MyApplication")
            return
        }

        binding = ActivitySavedCountriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this,
            SavedCountriesViewModelFactory(app.countriesRepository))
            .get(SavedCountriesViewModel::class.java)

        viewModel.countries.observe(this, Observer { countries ->
            val adapter = CountriesListAdapter(this, countries)
            binding.savedCountriesList.adapter = adapter
        })

        binding.savedCountriesList.isClickable = true
        binding.savedCountriesList.setOnItemClickListener { parent, view, position, id ->
            val country = viewModel.countries.value?.get(position)
            country?.let {
                val intent = Intent(this, CountriesListDetailActivity::class.java)
                intent.putExtra("COUNTRY_ID", it.id)
                intent.putExtra("COUNTRY_NAME", it.name.common)
                intent.putExtra("COUNTRY_FLAGS", it.flags.png)
                intent.putExtra("COUNTRY_POPULATION", it.population)
                intent.putExtra("COUNTRY_CONTINENT", it.continents.first())
                intent.putExtra("COUNTRY_CAPITAL", it.capital.first())
                intent.putExtra("COUNTRY_FLAG", it.flag)
                intent.putExtra("ACTION_TYPE", "DELETE")
                startActivity(intent)
            }
        }
    }
}
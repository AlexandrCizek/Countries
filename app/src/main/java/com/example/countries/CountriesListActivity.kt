package com.example.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countries.databinding.ActivityCountriesListBinding

class CountriesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesListBinding
    private lateinit var viewModel: CountriesListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContext = application
        val app = appContext as? MyApplication ?: run {
            Log.e("CountriesListActivity", "Application context is not MyApplication")
            return
        }

        binding = ActivityCountriesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this,
            CountriesListViewModelFactory(app.countriesRepository))
            .get(CountriesListViewModel::class.java)

        binding.countriesListViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.countries.observe(this, Observer { countries ->
            val adapter = CountriesListAdapter(this, countries)
            binding.countriesList.adapter = adapter
        })

        binding.countriesList.isClickable = true
        binding.countriesList.setOnItemClickListener { parent, view, position, id ->
            val country = viewModel.countries.value?.get(position)
            country?.let {
                val intent = Intent(this, CountriesListDetailActivity::class.java)
                intent.putExtra("COUNTRY_NAME", it.name.common)
                intent.putExtra("COUNTRY_FLAGS", it.flags.png)
                intent.putExtra("COUNTRY_POPULATION", it.population)
                intent.putExtra("COUNTRY_CONTINENT", it.continents.first())
                intent.putExtra("COUNTRY_CAPITAL", it.capital.first())
                intent.putExtra("COUNTRY_FLAG", it.flag)
                intent.putExtra("ACTION_TYPE", "SAVE")
                startActivity(intent)
            }
        }

        binding.searchButton.setOnClickListener {
            val inputText = binding.searchEditText.text.toString()
            viewModel.getCountriesByName(inputText)
            binding.searchEditText.setText("")
        }

        binding.europeButton.setOnClickListener {
            viewModel.getCountriesByRegion("Europe")
            binding.searchEditText.setText("")
        }

        binding.asiaButton.setOnClickListener {
            viewModel.getCountriesByRegion("Asia")
            binding.searchEditText.setText("")
        }

        binding.americaButton.setOnClickListener {
            viewModel.getCountriesByRegion("America")
            binding.searchEditText.setText("")
        }

        binding.africaButton.setOnClickListener {
            viewModel.getCountriesByRegion("Africa")
            binding.searchEditText.setText("")
        }

        binding.showSavedCountriesButton.setOnClickListener {
            val intent = Intent(this, SavedCountriesActivity::class.java)
            startActivity(intent)
        }
    }
}
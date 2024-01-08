package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countries.databinding.ActivityCountriesListBinding

class CountriesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)

        binding = ActivityCountriesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
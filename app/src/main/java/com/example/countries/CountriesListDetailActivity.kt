package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CountriesListDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.countries_list_detail)

        val countryName = intent.getStringExtra("COUNTRY_NAME")
        val countryPopulation = intent.getIntExtra("COUNTRY_POPULATION", 0)

        findViewById<TextView>(R.id.countryDetailName).text = countryName
        findViewById<TextView>(R.id.countryDetailPopulation).text = countryPopulation.toString()
    }
}
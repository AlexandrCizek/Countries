package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class CountriesListDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.countries_list_detail)

        val flagUrl = intent.getStringExtra("COUNTRY_FLAG")
        val name = intent.getStringExtra("COUNTRY_NAME")
        val population = intent.getIntExtra("COUNTRY_POPULATION", 0)
        val capital = intent.getStringExtra("COUNTRY_CAPITAL")
        val continent = intent.getStringExtra("COUNTRY_CONTINENT")

        val flagImage = findViewById<ImageView>(R.id.countryDetailFlag)
        Picasso.get().load(flagUrl).into(flagImage)

        findViewById<TextView>(R.id.countryDetailName).text = name
        findViewById<TextView>(R.id.countryDetailPopulation).text = "Citizens: " + formatPopulation(population)
        findViewById<TextView>(R.id.countryDetailCapital).text = "Capital: " + capital
        findViewById<TextView>(R.id.countryDetailContinent).text = "Continent: " + continent
    }

    private fun formatPopulation(population : Int) : String {
        return NumberFormat.getNumberInstance().format(population)
    }
}
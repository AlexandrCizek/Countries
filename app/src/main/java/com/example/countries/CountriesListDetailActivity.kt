package com.example.countries

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.countries.model.Country
import com.example.countries.model.CountryFlags
import com.example.countries.model.CountryName
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.text.NumberFormat

class CountriesListDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.countries_list_detail)

        val appContext = application
        val app = appContext as? MyApplication ?: run {
            Log.e("CountriesListActivity", "Application context is not MyApplication")
            return
        }

        val id = intent.getIntExtra("COUNTRY_ID", -1)
        val flagUrl:String = intent.getStringExtra("COUNTRY_FLAGS") ?: "No Flag Url"
        val flagEmoji = intent.getStringExtra("COUNTRY_FLAG") ?: "No Emoji"
        val name: String = intent.getStringExtra("COUNTRY_NAME") ?: "No Name"
        val population = intent.getIntExtra("COUNTRY_POPULATION", 0)
        val capital: String = intent.getStringExtra("COUNTRY_CAPITAL") ?: "No Capital"
        val continent: String = intent.getStringExtra("COUNTRY_CONTINENT") ?: "No Continent"
        val actionType: String = intent.getStringExtra("ACTION_TYPE")?: ""

        val flagImage = findViewById<ImageView>(R.id.countryDetailFlag)
        Picasso.get().load(flagUrl).into(flagImage)

        findViewById<TextView>(R.id.countryDetailName).text = name
        findViewById<TextView>(R.id.countryDetailPopulation).text = "Citizens: " + formatPopulation(population)
        findViewById<TextView>(R.id.countryDetailCapital).text = "Capital: " + capital
        findViewById<TextView>(R.id.countryDetailContinent).text = "Continent: " + continent

        val saveButton = findViewById<Button>(R.id.saveButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        if (actionType == "SAVE") {
            saveButton.visibility = View.VISIBLE
        } else if (actionType == "DELETE") {
            deleteButton.visibility = View.VISIBLE
        }

        saveButton.setOnClickListener {
            lifecycleScope.launch {
                try {
                    app.countriesRepository.insert(Country(
                        name= CountryName(name, name),
                        flag = flagEmoji,
                        flags = CountryFlags(flagUrl, flagUrl),
                        population = population,
                        continents = listOf(continent),
                        capital = listOf(capital)
                    ))
                    finish()
                } catch (e: SQLiteConstraintException) {
                    val alertDialogBuilder = AlertDialog.Builder(this@CountriesListDetailActivity)
                    alertDialogBuilder.setTitle("Ooops")
                    alertDialogBuilder.setMessage("You have already saved this country.")
                    alertDialogBuilder.setPositiveButton("OK") { dialog, which -> }
                    alertDialogBuilder.create().show()
                }

            }
        }

        deleteButton.setOnClickListener {
            lifecycleScope.launch {
                app.countriesRepository.delete(id)
                finish()
            }
        }
    }

    private fun formatPopulation(population : Int) : String {
        return NumberFormat.getNumberInstance().format(population)
    }
}
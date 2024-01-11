package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.countries.databinding.ActivityCountriesListBinding
import com.example.countries.databinding.ActivitySavedCountriesBinding

class SavedCountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedCountriesBinding

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
    }
}
package com.example.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val content = findViewById<LinearLayout>(R.id.splash_content)
        content.alpha = 0f
        content.animate().setDuration(2000).alpha(1f).withEndAction {
            val intent = Intent(this, CountriesListActivity::class.java)
            startActivity(intent)

        }
    }
}
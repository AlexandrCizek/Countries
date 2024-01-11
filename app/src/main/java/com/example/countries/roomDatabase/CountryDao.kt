package com.example.countries.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countries.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insert(country: Country)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): LiveData<List<Country>>
}

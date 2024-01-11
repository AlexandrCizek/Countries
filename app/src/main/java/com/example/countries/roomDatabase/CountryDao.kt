package com.example.countries.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countries.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(country: Country)

    @Query("DELETE FROM countries WHERE id = :countryId")
    suspend fun delete(countryId: Int)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<Country>>
}

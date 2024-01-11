package com.example.countries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "countries", indices = [Index(value = ["name"], unique = true)])
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: CountryName,
    val flag: String,
    val flags: CountryFlags,
    val population: Int,
    val continents: List<String>,
    val capital: List<String>
)

data class CountryName(
    val common: String,
    val official: String
)

data class CountryFlags(
    val png: String,
    val svg: String
)


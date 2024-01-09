package com.example.countries.model

data class Country(
    val name: CountryName,
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


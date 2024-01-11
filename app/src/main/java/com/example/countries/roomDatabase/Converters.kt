package com.example.countries.roomDatabase

import androidx.room.TypeConverter
import com.example.countries.model.CountryFlags
import com.example.countries.model.CountryName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCountryName(name: CountryName): String = gson.toJson(name)

    @TypeConverter
    fun toCountryName(name: String): CountryName = gson.fromJson(name, CountryName::class.java)

    @TypeConverter
    fun fromCountryFlags(flags: CountryFlags): String = gson.toJson(flags)

    @TypeConverter
    fun toCountryFlags(flags: String): CountryFlags = gson.fromJson(flags, CountryFlags::class.java)

    @TypeConverter
    fun fromStringList(list: List<String>): String = gson.toJson(list)

    @TypeConverter
    fun toStringList(data: String): List<String> {
        if (data.isBlank()) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }
}
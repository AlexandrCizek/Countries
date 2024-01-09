package com.example.countries

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.countries.model.Country

class CountriesListAdapter(private val context: Activity, private val countries: List<Country>) : ArrayAdapter<Country>(context, R.layout.countries_list_item, countries) {

    private class ViewHolder {
        var textView: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.countries_list_item, parent, false)
            viewHolder = ViewHolder()
            viewHolder.textView = view.findViewById(R.id.countryTextView)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val country = countries[position]
        viewHolder.textView?.text = "${country.flag} ${country.name.common}"

        return view
    }
}

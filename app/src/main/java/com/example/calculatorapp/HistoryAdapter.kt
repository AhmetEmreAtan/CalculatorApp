package com.example.calculatorapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryAdapter(context: Context, private val dataSource: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.list_item_history, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_history, parent, false)
        }

        val historyItemTextView = listItemView!!.findViewById<TextView>(R.id.textViewHistoryItem)
        historyItemTextView.text = getItem(position)

        return listItemView
    }
}

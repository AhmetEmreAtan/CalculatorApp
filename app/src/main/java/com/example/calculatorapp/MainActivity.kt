package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private var expression: String = ""
    private var result: Double = 0.0
    private lateinit var listViewHistory: ListView
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)
        listViewHistory = findViewById(R.id.listViewHistory)

        historyAdapter = HistoryAdapter(this, historyList)
        listViewHistory.adapter = historyAdapter
    }

    fun addToHistory(expression: String) {
        historyList.add(expression)
        historyAdapter.notifyDataSetChanged()
    }

    fun onNumberClick(view: View) {
        if (view is Button) {
            val number: String = view.text.toString()
            expression += number
            textViewResult.text = expression
        }
    }

    fun onOperatorClick(view: View) {
        if (view is Button) {
            val operator: String = view.text.toString()
            expression += operator
            textViewResult.text = expression
        }
    }

    fun onClearClick(view: View) {
        expression = ""
        textViewResult.text = "0"
    }

    fun onCalculateClick(view: View) {
        try {
            result = evaluateExpression(expression)
            textViewResult.text = result.toString()
            expression = result.toString()
            addToHistory(expression)
        } catch (e: Exception) {
            textViewResult.text = "Error"
            expression = ""
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val parts = expression.split("(?<=[-+*/])|(?=[-+*/])".toRegex())
        var result = parts[0].toDouble()
        for (i in 1 until parts.size step 2) {
            val operator = parts[i]
            val operand = parts[i + 1].toDouble()
            result = when (operator) {
                "+" -> result + operand
                "-" -> result - operand
                "*" -> result * operand
                "/" -> result / operand
                else -> throw IllegalArgumentException("Invalid operator")
            }
        }
        return result
    }
}

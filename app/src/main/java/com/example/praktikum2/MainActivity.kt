package com.example.praktikum2

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstNumber: EditText
    private lateinit var etSecondNumber: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResultAdapter
    private val resultsList = mutableListOf<CalculationResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etFirstNumber = findViewById(R.id.etFirstNumber)
        etSecondNumber = findViewById(R.id.etSecondNumber)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        radioGroup = findViewById(R.id.radioGroup)
        recyclerView = findViewById(R.id.recyclerViewResults)

        adapter = ResultAdapter(resultsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        if (savedInstanceState != null) {
            tvResult.text = savedInstanceState.getString("savedText", "Result will appear here")
        }

        btnCalculate.setOnClickListener {
            val firstNum = etFirstNumber.text.toString().toIntOrNull()
            val secondNum = etSecondNumber.text.toString().toIntOrNull()

            if (firstNum == null || secondNum == null) {
                tvResult.text = "Please enter valid numbers"
                return@setOnClickListener
            }

            val selectedOperation = radioGroup.checkedRadioButtonId
            val result = when (selectedOperation) {
                R.id.radioAdd -> firstNum + secondNum
                R.id.radioSubtract -> firstNum - secondNum
                R.id.radioMultiply -> firstNum * secondNum
                R.id.radioDivide -> if (secondNum != 0) firstNum / secondNum else "Cannot divide by zero"
                else -> "Please select an operation"
            }

            val resultText = "Result: $result"
            tvResult.text = resultText

            adapter.addResult(CalculationResult(resultText))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedText", tvResult.text.toString())
    }
}

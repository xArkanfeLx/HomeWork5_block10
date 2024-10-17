package com.example.intent_activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTV: TextView
    private lateinit var value1ET: EditText
    private lateinit var value2ET: EditText
    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button
    private lateinit var buttonMultBTN: Button
    private lateinit var buttonDivBTN: Button
    private lateinit var sendResultBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTV = findViewById(R.id.resultTV)
        value1ET = findViewById(R.id.value1ET)
        value2ET = findViewById(R.id.value2ET)
        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDifBTN = findViewById(R.id.buttonDifBTN)
        buttonMultBTN = findViewById(R.id.buttonMultBTN)
        buttonDivBTN = findViewById(R.id.buttonDivBTN)
        sendResultBTN = findViewById(R.id.sendResultBTN)

        buttonSumBTN.setOnClickListener(this)
        buttonDifBTN.setOnClickListener(this)
        buttonMultBTN.setOnClickListener(this)
        buttonDivBTN.setOnClickListener(this)

        sendResultBTN.setOnClickListener {
            val text =  resultTV.text
            if(text.isEmpty()){
                Toast.makeText(this, "Результат не посчитан", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = text.toString().trim()
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onClick(p0: View?) {
        val text1 = value1ET.text
        val text2 = value2ET.text
        val val1 = text1.toString().toIntOrNull()
        val val2 = text2.toString().toIntOrNull()
        if ((val1 == null || val2 == null) || (text1.isEmpty() || text2.isEmpty())) {
            Toast.makeText(this, "Неверные значения", Toast.LENGTH_SHORT).show()
            return
        }
        when (p0?.id) {
            R.id.buttonSumBTN -> {
                resultTV.text = (val1 + val2).toString()
            }

            R.id.buttonDifBTN -> {
                resultTV.text = (val1 - val2).toString()
            }

            R.id.buttonMultBTN -> {
                resultTV.text = (val1 * val2).toString()
            }

            R.id.buttonDivBTN -> {
                resultTV.text = (val1 / val2).toString()
            }
        }
    }
}
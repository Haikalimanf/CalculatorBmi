package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editTextBerat: EditText
    private lateinit var editTextTinggi: EditText
    private lateinit var btnCalculate: Button
    private lateinit var pria: RadioButton
    private lateinit var wanita: RadioButton
    private lateinit var hasilBMI: TextView
    private lateinit var hasilIndexBadan: TextView
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var hasilProtein: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextBerat = findViewById(R.id.InputBerat)
        editTextTinggi = findViewById(R.id.inputTinggi)
        btnCalculate = findViewById(R.id.button_calculate)
        pria = findViewById(R.id.Checkboxlaki)
        wanita = findViewById(R.id.CheckboxWanita)
        hasilBMI = findViewById(R.id.resultBMI)
        hasilIndexBadan = findViewById(R.id.resultIndexBerat)
        radioGroupGender = findViewById(R.id.option_jenis)
        hasilProtein = findViewById(R.id.resultProtein)
        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_calculate) {
            val berat: Double = editTextBerat.text.toString().toDouble()
            val tinggi: Double = editTextTinggi.text.toString().toDouble() / 100
            val gender: String = getSelectedGender()
            val kebutuhanProteinPerKg = 1.2
            val bmi: Double = if (gender == "Male") {
                berat / (tinggi * tinggi)
            } else {
                berat / (tinggi * tinggi) + 1
            }

            val kebutuhanProteinHarian: Double = if (gender == "Male") {
                berat * 1.2
            } else {
                berat * 1.0
            }

            // Menampilkan nilai BMI dan status BMI
            val decimalFormat = DecimalFormat("#.##")
            val formatBMI = decimalFormat.format(bmi)
            hasilBMI.text = formatBMI.toString()
            val IndexBadan = getStatusBMI(bmi)
            hasilIndexBadan.text = IndexBadan.toString()
            hasilProtein.text = String.format("%.1f", kebutuhanProteinHarian)
        }
    }

    private fun getSelectedGender(): String {
        val selectedRadioButtonId: Int = radioGroupGender.checkedRadioButtonId
        return when (selectedRadioButtonId) {
            pria.id -> "Male"
            wanita.id -> "Female"
            else -> "Unknown"
        }
    }


    private fun getStatusBMI(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Kurus"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Gendut"
            else -> "Obesitas"
        }
    }
}
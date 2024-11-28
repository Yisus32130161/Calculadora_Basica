package com.Qate.calculadora_basica

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1 = findViewById<EditText>(R.id.edt1)
        val number2 = findViewById<EditText>(R.id.edt2)
        val spinner = findViewById<Spinner>(R.id.operaciones)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val resultText = findViewById<TextView>(R.id.resultado)

        // Obtener las opciones del Spinner desde strings.xml
        val operations = resources.getStringArray(R.array.options)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)

        calculateButton.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            val operation = spinner.selectedItem.toString()

            // Verificar que los números sean válidos
            if (num1 == null || num2 == null) {
                resultText.text = getString(R.string.error_invalid_numbers)
                return@setOnClickListener
            }

            // Realizar la operación seleccionada
            val result = when (operation) {
                getString(R.string.addition) -> num1 + num2
                getString(R.string.subtraction) -> num1 - num2
                getString(R.string.multiplication) -> num1 * num2
                getString(R.string.division) -> {
                    if (num2 == 0.0) {
                        resultText.text = getString(R.string.error_divide_by_zero)
                        return@setOnClickListener
                    } else num1 / num2
                }
                else -> 0.0
            }

            // Mostrar el resultado
            resultText.text = "${getString(R.string.result)} $result"
        }
    }
}

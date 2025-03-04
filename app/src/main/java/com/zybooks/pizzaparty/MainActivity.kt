package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private lateinit var pizzaSize: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Access to the UI View objects
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        pizzaSize = findViewById(R.id.pizza_size)
        numPizzasTextView.setText("")
    }

    fun calculateClick(view: View) {
        // Get the number of people from the UI (as an Int)
        val numAttendStr = numAttendEditText.text.toString()
        val numAttend = numAttendStr.toIntOrNull() ?: 0 //Fixes app crash by providing a default value if null

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }
        val size = when (pizzaSize.getCheckedRadioButtonId()){
           R.id.small_radio_button -> PizzaCalculator.SizeOfPizza.SMALL
            R.id.medium_radio_button2 -> PizzaCalculator.SizeOfPizza.MEDIUM
            else -> PizzaCalculator.SizeOfPizza.LARGE
        }
        // Get the number of pizzas needed using the Model: Pizza Calculator
        val calculator = PizzaCalculator(numAttend,hungerLevel, size)
        val totalPizzas = calculator.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas,totalPizzas)
        numPizzasTextView.setText(totalText)
    }
}
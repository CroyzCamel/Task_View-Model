package com.example.task_view_model.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class DemoViewModel : ViewModel() {
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun switchChange() {
        isFahrenheit = !isFahrenheit
    }

    fun converterTemp(temp: String) {
        result = try {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                ((tempInt * 1.8)+ 32).roundToInt().toString()
            } else {
                ((tempInt -32) * 0.5559).roundToInt().toString()
            }
        } catch (e: Exception) {
            "Invalid Input"
        }
    }


}
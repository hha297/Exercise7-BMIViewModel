package com.example.bmiviewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class BMIViewModel : ViewModel() {
    var height: Float = 0f
    var weight: Float = 0f
    var bmi: MutableState<Float> = mutableStateOf(0f)

    fun calculateBMI() {
        if (height > 0 && weight > 0) {
            val heightInMeter = height / 100 // Convert height from cm to meters
            val calculatedBMI = weight / (heightInMeter * heightInMeter)
            bmi.value = calculatedBMI
        }
    }
}
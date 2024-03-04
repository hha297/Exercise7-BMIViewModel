package com.example.bmiviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmiviewmodel.ui.theme.BMIViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIApp()
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BMIApp() {
    val viewModel: BMIViewModel = viewModel()
    val heightState = remember { mutableStateOf("") }
    val weightState = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = heightState.value,
            onValueChange = { heightState.value = it },
            label = { Text("Height (cm)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = weightState.value,
            onValueChange = { weightState.value = it },
            label = { Text("Weight (kg)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.height = heightState.value.toFloatOrNull() ?: 0f
            viewModel.weight = weightState.value.toFloatOrNull() ?: 0f
            viewModel.calculateBMI()
            keyboardController?.hide()
        }) {
            Text("Calculate BMI")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "BMI: ${viewModel.bmi.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBMIApp() {
    BMIApp()
}
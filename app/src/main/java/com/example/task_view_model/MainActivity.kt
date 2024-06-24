package com.example.task_view_model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task_view_model.model.DemoViewModel
import com.example.task_view_model.ui.InputRow
import com.example.task_view_model.ui.theme.Task_ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task_ViewModelTheme {
                ScreenSetup()
            }
        }
    }
}

@Composable
fun ScreenSetup(
    viewModel: DemoViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    MainScreen(
        modifier = modifier,
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.result,
        convertTemp = { viewModel.converterTemp(it) },
        switchChange = { viewModel.switchChange() }
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isFahrenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit,
    switchChange: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        var textState by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textState = text
        }

        Text(
            text = "Temperature Converter",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        InputRow(
            isFahrenheit = isFahrenheit,
            textState = textState,
            switchChange = { switchChange() },
            onTextChange = onTextChange
        )

        Text(
            text = if (isFahrenheit) {
                "Temp in fahrenheit: $result \u2109"
            } else {
                "Temp in celsius: $result \u2103"
            },
            modifier = modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = { convertTemp(textState) }) {
            Text("Convert Temperature")
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ScreenPreview(model: DemoViewModel = viewModel()) {
    Task_ViewModelTheme {
        MainScreen(
            isFahrenheit = model.isFahrenheit,
            result = model.result,
            convertTemp = { model.converterTemp(it) },
            switchChange = { model.switchChange() }

        )
    }
}




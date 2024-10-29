package com.example.simple_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simple_list.ui.theme.SimplelistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimplelistTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var input by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf(0) }
    var numbersList by remember { mutableStateOf(listOf<Int>()) }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { newInput ->
                // Kiểm tra xem input có phải là số nguyên dương không
                if (newInput.all { it.isDigit() }) {
                    input = newInput
                }
            },
            label = { Text("Nhập số nguyên dương") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) // Chỉ hiển thị bàn phím số
        )
        Spacer(modifier = Modifier.height(8.dp))

        // RadioButtons for Even, Odd, Perfect Squares
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == 0,
                    onClick = { selectedOption = 0 }
                )
                Text("Số chẵn từ 0 -> n", modifier = Modifier.padding(start = 8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedOption == 1,
                    onClick = { selectedOption = 1 }
                )
                Text("Số lẻ từ 1 -> n", modifier = Modifier.padding(start = 8.dp))

            }
            Spacer(modifier = Modifier.width(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedOption == 2,
                    onClick = { selectedOption = 2 }
                )
                Text("Số chính phương từ 0 -> n", modifier = Modifier.padding(start = 8.dp))
            }

        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            // Convert input to integer and generate list based on selection
            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                errorMessage = "Please enter a valid positive integer"
                numbersList = emptyList()
            } else {
                errorMessage = ""
                numbersList = when (selectedOption) {
                    0 -> (0..n).filter { it % 2 == 0 } // Even numbers
                    1 -> (1..n).filter { it % 2 != 0 } // Odd numbers
                    2 -> (0..n).filter {
                        kotlin.math.sqrt(it.toFloat()).toInt().toFloat() == kotlin.math.sqrt(it.toFloat())
                    } // Perfect squares
                    else -> emptyList()
                }
            }
        }) {
            Text("Show")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(10),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(numbersList.size) { index ->
                    Text(
                        text = numbersList[index].toString(),
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SimplelistTheme {
        MainScreen()
    }
}

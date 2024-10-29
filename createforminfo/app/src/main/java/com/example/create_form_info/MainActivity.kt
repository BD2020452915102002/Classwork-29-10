package com.example.create_form_info

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.create_form_info.ui.theme.CreateforminfoTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateforminfoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FormScreen(modifier: Modifier = Modifier) {
    var studentId by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var birthDate by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var likesSports by remember { mutableStateOf(false) }
    var likesMovies by remember { mutableStateOf(false) }
    var likesMusic by remember { mutableStateOf(false) }
    var isAgreed by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Tạo DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, selectedYear, selectedMonth, selectedDay ->
            birthDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text("MSSV") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Họ tên") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc nếu cần
        ) {
            Text("Giới tính:")
            Spacer(modifier = Modifier.width(8.dp)) // Thêm khoảng cách nếu cần
            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" }
            )
            Text("Nam")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" }
            )
            Text("Nữ")
        }


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Số điện thoại") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        OutlinedTextField(
            value = birthDate,
            onValueChange = {},
            label = { Text("Ngày sinh") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            readOnly = true
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Địa chỉ") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Checkbox(
                checked = likesSports,
                onCheckedChange = { likesSports = it }
            )
            Text("Thể thao")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = likesMovies,
                onCheckedChange = { likesMovies = it }
            )
            Text("Điện ảnh")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = likesMusic,
                onCheckedChange = { likesMusic = it }
            )
            Text("Âm nhạc")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isAgreed,
                onCheckedChange = { isAgreed = it }
            )
            Text("Đồng ý với các điều khoản")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}".toRegex()
                val phonePattern = "^(03|05|07|08|09)[0-9]{8}\$".toRegex()

                when {
                    studentId.isBlank() || fullName.isBlank() || email.isBlank() ||
                            phoneNumber.isBlank() || birthDate.isBlank() || address.isBlank() -> {
                        errorMessage = "Vui lòng điền đầy đủ thông tin."
                    }
                    !email.matches(emailPattern) -> {
                        errorMessage = "Email không hợp lệ."
                    }
                    !phoneNumber.matches(phonePattern) -> {
                        errorMessage = "Số điện thoại không hợp lệ. Phải là số điện thoại Việt Nam 10 chữ số."
                    }
                    !isAgreed -> {
                        errorMessage = "Bạn cần đồng ý với các điều khoản."
                    }
                    else -> {
                        errorMessage = ""
                        // Xử lý khi thông tin đã đầy đủ và hợp lệ
                    }
                }
            },
            enabled = isAgreed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    CreateforminfoTheme {
        FormScreen()
    }
}

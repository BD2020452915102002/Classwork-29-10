package com.example.find_in_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.find_in_list.ui.theme.FindinlistTheme

data class Student(val hoTen: String, val mssv: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindinlistTheme {
                StudentListScreen()
            }
        }
    }
}

@Composable
fun StudentListScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val studentList = generateStudentList()
    val filteredList = studentList.filter {
        it.hoTen.contains(searchQuery.text, ignoreCase = true) || it.mssv.contains(searchQuery.text)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Tìm kiếm sinh viên") }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(filteredList) { student ->
                StudentItem(student = student)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Text(text = "Họ và tên: ${student.hoTen}")
        Text(text = "MSSV: ${student.mssv}")
    }
}

fun generateStudentList(): List<Student> {
    return listOf(
        Student("Nguyễn Văn A", "20200001"),
        Student("Lê Thị B", "20200002"),
        Student("Trần Văn C", "20200003"),
        Student("Phạm Thị D", "20200004"),
        Student("Đặng Văn E", "20200005"),
        Student("Nguyễn Thị F", "20200006"),
        Student("Lê Văn G", "20200007"),
        Student("Trần Thị H", "20200008"),
        Student("Phạm Văn I", "20200009"),
        Student("Đặng Thị J", "20200010"),
        Student("Nguyễn Văn K", "20200011"),
        Student("Lê Thị L", "20200012"),
        Student("Trần Văn M", "20200013"),
        Student("Phạm Thị N", "20200014"),
        Student("Đặng Văn O", "20200015"),
        Student("Nguyễn Thị P", "20200016"),
        Student("Lê Văn Q", "20200017"),
        Student("Trần Thị R", "20200018"),
        Student("Phạm Văn S", "20200019"),
        Student("Đặng Thị T", "20200020"),
        Student("Nguyễn Văn U", "20200021"),
        Student("Lê Thị V", "20200022"),
        Student("Trần Văn W", "20200023"),
        Student("Phạm Thị X", "20200024"),
        Student("Đặng Văn Y", "20200025"),
        Student("Nguyễn Thị Z", "20200026"),
        Student("Lê Văn A1", "20200027"),
        Student("Trần Thị B1", "20200028"),
        Student("Phạm Văn C1", "20200029"),
        Student("Đặng Thị D1", "20200030"),
        Student("Nguyễn Văn E1", "20200031"),
        Student("Lê Thị F1", "20200032"),
        Student("Trần Văn G1", "20200033"),
        Student("Phạm Thị H1", "20200034"),
        Student("Đặng Văn I1", "20200035"),
        Student("Nguyễn Thị J1", "20200036"),
        Student("Lê Văn K1", "20200037"),
        Student("Trần Thị L1", "20200038"),
        Student("Phạm Văn M1", "20200039"),
        Student("Đặng Thị N1", "20200040"),
        Student("Nguyễn Văn O1", "20200041"),
        Student("Lê Thị P1", "20200042"),
        Student("Trần Văn Q1", "20200043"),
        Student("Phạm Thị R1", "20200044"),
        Student("Đặng Văn S1", "20200045"),
        Student("Nguyễn Thị T1", "20200046"),
        Student("Lê Văn U1", "20200047"),
        Student("Trần Thị V1", "20200048"),
        Student("Phạm Văn W1", "20200049"),
        Student("Đặng Thị X1", "20200050")
    )
}

@Preview(showBackground = true)
@Composable
fun StudentListScreenPreview() {
    FindinlistTheme {
        StudentListScreen()
    }
}

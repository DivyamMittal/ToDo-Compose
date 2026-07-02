package com.example.todoapp_compose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp_compose.data.room_database.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoBottomSheet(
    taskItem: TodoItem?,
    onDismissRequest: () -> Unit,
    onSave: (String) -> Unit
) {
    var taskTitle by rememberSaveable { mutableStateOf(taskItem?.title ?: "") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .navigationBarsPadding()
                .imePadding()
        ) {
            Text( if(taskItem== null) "Add To-do" else "Update To-do", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                placeholder = {
                    Text("Add ToDo here..")
                },
                maxLines = 5,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            // button
            Button(
                onClick = {
                    onSave(taskTitle.trim())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                enabled = taskTitle.trim().isNotEmpty()
            ) {
                Text("Save")
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
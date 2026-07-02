package com.example.todoapp_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp_compose.data.room_database.TodoItem

@Composable
fun TodoItemTile(todoItem: TodoItem, onClickItem: ()-> Unit, onClickEdit:() -> Unit, onClickDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = 0.3f)
        ),
        onClick = onClickItem
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = if(todoItem.isDone) Icons.Default.RadioButtonChecked else Icons.Default.RadioButtonUnchecked, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))

            Text(todoItem.title, modifier = Modifier.weight(1f),
                textDecoration = if(todoItem.isDone) TextDecoration.LineThrough else null
            )

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = {
                    onClickEdit()
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(
                onClick = {
                    onClickDelete()
                }
            ) {
                Icon(imageVector = Icons.Default.DeleteOutline, contentDescription = null)
            }
        }
    }
}
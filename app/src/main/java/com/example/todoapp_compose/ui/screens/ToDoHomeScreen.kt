package com.example.todoapp_compose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp_compose.data.room_database.TodoItem
import com.example.todoapp_compose.viewModel.TodoViewModel


@Composable
 fun ToDoHomeScreen(viewModel: TodoViewModel) {

     val toDoItems = viewModel.allTasks.collectAsState(initial = emptyList()).value
     var showTodoBottomSheet by remember{ mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<TodoItem?>(null) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    showTodoBottomSheet = true
                },
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

                Spacer(modifier = Modifier.width(5.dp))

                Text("Add Task")
            }
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 30.dp)
        ) {

            Text("ToDo Items", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            // List of to-do Items tiles
            if(toDoItems.isEmpty())
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No To-do Items")
                }
            else
                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),

                ) {
                    itemsIndexed(toDoItems) { index, item ->
                        TodoItemTile(
                            todoItem = item,
                            onClickItem = {
                                viewModel.updateTodoItem(item.copy(isDone = !item.isDone))
                            },
                            onClickEdit = {
                                showTodoBottomSheet = true
                                selectedItem = item
                            },
                            onClickDelete = {
                                viewModel.deleteTodoItem(item)

                                Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }


        }

        if(showTodoBottomSheet)
            TodoBottomSheet(
                onDismissRequest = {
                    showTodoBottomSheet = false
                    selectedItem = null
                },

                onSave = { taskTitle ->
                    if(selectedItem == null){
                        viewModel.insertTodoItem(item = TodoItem(title = taskTitle))
                        showTodoBottomSheet = false
                    }else{
                        viewModel.updateTodoItem(selectedItem!!.copy(title = taskTitle))
                        showTodoBottomSheet = false
                    }

                },
                taskItem = selectedItem
            )
    }
}
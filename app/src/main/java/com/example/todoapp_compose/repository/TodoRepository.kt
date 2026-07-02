package com.example.todoapp_compose.repository

import com.example.todoapp_compose.data.room_database.TodoDao
import com.example.todoapp_compose.data.room_database.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoRepository(private var dao: TodoDao) {

    // Get All to-do Tasks
    fun getAllTasks() : Flow<List<TodoItem>>{
        return dao.getTodoItems()
    }

    // Insert to-do Item
   suspend fun insertItem(todoItem: TodoItem) = dao.insertTodoItem(todoItem)

    // Update to-do item
   suspend fun updateItem(todoItem: TodoItem) = dao.updateTodoItem(todoItem)

    // Delete to-do Item
   suspend fun deleteItem(todoItem: TodoItem) = dao.deleteTodoItem(todoItem)

}
package com.example.todoapp_compose.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp_compose.data.room_database.TodoDatabase
import com.example.todoapp_compose.data.room_database.TodoItem
import com.example.todoapp_compose.repository.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private var application: Application) : AndroidViewModel(application) {

    private val dao = TodoDatabase.getDatabase(application.applicationContext).todoDao()

    private val repository = TodoRepository(dao)

    val allTasks : StateFlow<List<TodoItem>> = repository.getAllTasks().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun insertTodoItem(item: TodoItem){
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun updateTodoItem(item: TodoItem){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun deleteTodoItem(item: TodoItem){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

}
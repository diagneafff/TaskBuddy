package com.papb.taskbuddy

import Task
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val taskDao = TaskDatabase.getDatabase().taskDao()
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }
}

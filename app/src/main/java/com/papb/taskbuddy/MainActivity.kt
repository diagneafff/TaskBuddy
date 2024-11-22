package com.papb.taskbuddy

import AddTaskActivity
import TaskAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.papb.taskbuddy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView and Adapter
        setupRecyclerView()

        // Observe Tasks
        observeTasks()

        // Setup Add Task Button
        setupAddTaskButton()
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(emptyList()) { task ->
            // Handle task item click or update
            taskViewModel.updateTask(task)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }

    private fun observeTasks() {
        taskViewModel.allTasks.observe(this) { tasks ->
            // Update adapter with new list of tasks
            taskAdapter.updateTasks(tasks)
        }
    }

    private fun setupAddTaskButton() {
        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
//    private lateinit var binding: ActivityMainBinding
//    private val taskViewModel: TaskViewModel by viewModels()
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Set up RecyclerView
//        val adapter = TaskAdapter(emptyList()) { task ->
//            taskViewModel.updateTask(task)
//        }
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = adapter
//
//        // Observe LiveData
//        taskViewModel.allTasks.observe(this) { tasks ->
//            adapter.notifyDataSetChanged()
//        }
//
//        // Add task button
//        binding.addTaskButton.setOnClickListener {
//            startActivity(Intent(this, AddTaskActivity::class.java))
//        }
//    }
}


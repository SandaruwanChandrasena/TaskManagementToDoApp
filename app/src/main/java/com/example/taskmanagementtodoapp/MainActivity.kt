package com.example.taskmanagementtodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagementtodoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDatabaseHelper
    private lateinit var notesAdapter: TasksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = TaskDatabaseHelper(this)

        // Initialize the adapter with all notes from the database
        notesAdapter = TasksAdapter(db.getAllNotes(),this)

        // Set the layout manager and adapter for the RecyclerView
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        // Set up click listener for the add button
        binding.addButton.setOnClickListener{


            // Create an intent to navigate to the AddTaskActivity
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh data in the adapter when the activity resumes
        notesAdapter.refreshData(db.getAllNotes())
    }
}
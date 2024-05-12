package com.example.taskmanagementtodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementtodoapp.databinding.ActivityAddTaskBinding
import com.example.taskmanagementtodoapp.databinding.ActivityMainBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db : TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = TaskDatabaseHelper(this)

        // Set up click listener for the save button
        binding.saveButton.setOnClickListener {
            val title = binding.titleEdittext.text.toString()
            val content = binding.contentEditText.text.toString()

            // Create a Note object with the entered title and content
            val note = Task(0,title,content)

            // Insert the note into the database
            db.insertNote(note)

            // Finish the activity (close the AddNoteActivity)
            finish()

            // Show a toast message indicating that the note is saved
            Toast.makeText(this,"Your Task Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
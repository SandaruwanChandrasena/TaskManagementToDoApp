package com.example.taskmanagementtodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementtodoapp.databinding.ActivityUpdateTaskBinding

// Activity for updating a task
class UpdateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db : TaskDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using ViewBinding
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the database helper
        db = TaskDatabaseHelper(this)

        // Get the note ID passed from the previous activity
        noteId = intent.getIntExtra("note_id",-1)
        // If noteId is invalid, finish the activity
        if (noteId == -1){
            finish()
            return
        }

        // Get the note from the database based on its ID
        val note = db.getNoteById(noteId)
        // Set the text of EditText fields to display the existing note's title and content
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        // Set a click listener for the save button
        binding.updateSaveButton.setOnClickListener {
            // Retrieve the new title and content from EditText fields
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            // Create a new Task object with the updated information
            val updatedNote = Task(noteId, newTitle, newContent)
            // Update the task in the database
            db.updateNote(updatedNote)
            // Finish the activity
            finish()
            // Show a toast message indicating that changes have been saved
            Toast.makeText(this,"Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}

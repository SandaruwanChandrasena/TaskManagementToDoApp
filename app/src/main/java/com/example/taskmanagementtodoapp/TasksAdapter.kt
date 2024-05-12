package com.example.taskmanagementtodoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private var notes: List<Task>, context: Context) : RecyclerView.Adapter<TasksAdapter.NoteViewHolder>() {

    // Initialize the database helper
    private val db: TaskDatabaseHelper = TaskDatabaseHelper(context)

    // Define a ViewHolder for the RecyclerView
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    // Create a ViewHolder by inflating the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return NoteViewHolder(view)
    }

    // Return the number of items in the list
    override fun getItemCount(): Int = notes.size

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Set click listener for the update button
        holder.updateButton.setOnClickListener {
            // Create an intent to start UpdateTaskActivity
            val intent = Intent(holder.itemView.context, UpdateTaskActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for the delete button
        holder.deleteButton.setOnClickListener {
            // Delete the note from the database
            db.deleteNote(note.id)
            // Refresh the data in the adapter
            refreshData(db.getAllNotes())
            // Show a toast message indicating deletion
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to refresh the data in the adapter
    fun refreshData(newNotes: List<Task>){
        notes = newNotes
        notifyDataSetChanged()
    }
}

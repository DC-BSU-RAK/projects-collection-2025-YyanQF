package com.example.purrfect_multipage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class todoActivityView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_todo_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupStickyNote(findViewById(R.id.todo1), findViewById(R.id.buttonNote1), "note1")
        setupStickyNote(findViewById(R.id.todo2), findViewById(R.id.buttonNote2), "note2")
        setupStickyNote(findViewById(R.id.todo3), findViewById(R.id.buttonNote3), "note3")

        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupStickyNote(noteView: TextView, button: Button, key: String) {
        val prefs = getSharedPreferences("StickyNotes", MODE_PRIVATE)
        val savedText = prefs.getString(key, "Type something...")
        noteView.text = savedText

        noteView.setOnClickListener {
            val editText = EditText(this)
            editText.setText(noteView.text)

            AlertDialog.Builder(this)
                .setTitle("Add To-Do")
                .setView(editText)
                .setPositiveButton("Save") { _, _ ->
                    val newText = editText.text.toString()
                    noteView.text = newText
                    prefs.edit().putString(key, newText).apply()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        button.setOnClickListener {
            val intent = Intent(this, ongoingActivityView::class.java)
            intent.putExtra("noteText", noteView.text.toString())
            startActivity(intent)
        }
    }
}



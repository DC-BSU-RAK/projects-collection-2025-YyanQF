package com.example.purrfect_multipage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ongoingActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongoing_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("StickyNotes", MODE_PRIVATE)

        val note1 = prefs.getString("note1", "No note yet")
        val note2 = prefs.getString("note2", "No note yet")
        val note3 = prefs.getString("note3", "No note yet")

        val ongoing1 = findViewById<TextView>(R.id.ongoing1)
        val ongoing2 = findViewById<TextView>(R.id.ongoing2)
        val ongoing3 = findViewById<TextView>(R.id.ongoing3)

        ongoing1.text = note1
        ongoing2.text = note2
        ongoing3.text = note3

        // Completed Buttons
        val buttonNote4 = findViewById<Button>(R.id.buttonNote4)
        val buttonNote5 = findViewById<Button>(R.id.buttonNote5)
        val buttonNote6 = findViewById<Button>(R.id.buttonNote6)

        buttonNote4.setOnClickListener {
            openCompletedActivity(note1 ?: "No note yet")
        }

        buttonNote5.setOnClickListener {
            openCompletedActivity(note2 ?: "No note yet")
        }

        buttonNote6.setOnClickListener {
            openCompletedActivity(note3 ?: "No note yet")
        }

        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun openCompletedActivity(note: String) {
        val intent = Intent(this, completedActivityView::class.java)
        intent.putExtra("completedNote", note)
        startActivity(intent)
    }
}

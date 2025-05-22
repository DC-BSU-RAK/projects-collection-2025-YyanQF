package com.example.purrfect_multipage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // navigation buttons
        val button1 = findViewById<Button>(R.id.btn_todo)
        val button2 = findViewById<Button>(R.id.btn_ongoing)
        val button3 = findViewById<Button>(R.id.btn_completed)
        val button5 = findViewById<ImageButton>(R.id.btn_home)
        val button6 = findViewById<ImageButton>(R.id.btn_settings)

        button1.setOnClickListener {
            val intent = Intent(this, todoActivityView::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, ongoingActivityView::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, completedActivityView::class.java)
            startActivity(intent)
        }


        button5.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button6.setOnClickListener {
            val intent = Intent(this, settingsActivityView::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // shared preferences for user id
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = prefs.getString("username", "User")

        // basic welcome along size user id
        val helloTextView = findViewById<TextView>(R.id.helloTextView)
        helloTextView.text = "Meow, $username"

        val instructButton: Button = findViewById(R.id.instructButton)
        instructButton.setOnClickListener {
            val intent = Intent(this, popupActivity::class.java)
            startActivity(intent)
        }

    }
}

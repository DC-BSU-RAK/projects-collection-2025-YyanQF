package com.example.purrfect_multipage

import android.content.Intent
import android.content.SharedPreferences
import android.widget.ImageButton
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class settingsActivityView : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_view)

        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val usernameInput = findViewById<EditText>(R.id.editUsername)
        val passwordInput = findViewById<EditText>(R.id.editPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Save to SharedPreferences
                prefs.edit().putString("username", username).apply()

                Toast.makeText(this, "Good Meow, $username! welcome to Purrfect", Toast.LENGTH_SHORT).show()

                // Go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }

        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
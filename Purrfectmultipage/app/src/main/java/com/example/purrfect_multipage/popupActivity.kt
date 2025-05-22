package com.example.purrfect_multipage

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class popupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        val background = findViewById<View>(R.id.modalBackground)
        background.setOnClickListener {
            finish()
        }

        val closeBtn = findViewById<Button>(R.id.closeModalButton)
        closeBtn.setOnClickListener {
            finish()
        }
    }
}
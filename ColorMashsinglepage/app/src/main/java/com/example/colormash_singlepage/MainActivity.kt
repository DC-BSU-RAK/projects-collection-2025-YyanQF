package com.example.colormash_singlepage

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedColor: String? = null
    private var selectedCategory: String? = null

    private lateinit var selectionText: TextView
    private lateinit var resultText: TextView
    private lateinit var resultImage: ImageView

    // for the pop-up modal!
    private lateinit var modalContainer: FrameLayout
    private lateinit var modalClose: Button
    private lateinit var instructionsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectionText = findViewById(R.id.selected)
        resultText = findViewById(R.id.result_text)
        resultImage = findViewById(R.id.result_image)

        // Modal
        modalContainer = findViewById(R.id.modal_container)
        modalClose = findViewById(R.id.modal_close)
        instructionsButton = findViewById(R.id.instructions_button)

        instructionsButton.setOnClickListener {
            modalContainer.visibility = View.VISIBLE
        }

        modalClose.setOnClickListener {
            modalContainer.visibility = View.GONE
        }

        // color selections
        findViewById<ImageButton>(R.id.red_button).setOnClickListener {
            selectedColor = "Red"
            updateSelection()
        }

        findViewById<ImageButton>(R.id.blue_button).setOnClickListener {
            selectedColor = "Blue"
            updateSelection()
        }

        findViewById<ImageButton>(R.id.green_button).setOnClickListener {
            selectedColor = "Green"
            updateSelection()
        }

        // categories
        findViewById<Button>(R.id.fruit_btn).setOnClickListener {
            selectedCategory = "Fruit"
            updateSelection()
            showResult()
        }

        findViewById<Button>(R.id.drink_btn).setOnClickListener {
            selectedCategory = "Drink"
            updateSelection()
            showResult()
        }

        findViewById<Button>(R.id.cartoon_btn).setOnClickListener {
            selectedCategory = "Cartoon"
            updateSelection()
            showResult()
        }
    }

    private fun updateSelection() {
        val colorText = selectedColor ?: "[none]"
        val categoryText = selectedCategory ?: "[none]"
        selectionText.text = "Selection: $colorText + $categoryText"
    }
// dictionary basically of the color and catergory combinations
    private fun showResult() {
        if (selectedColor == null || selectedCategory == null) {
            Toast.makeText(this, "Please pick both a color and a category", Toast.LENGTH_SHORT).show()
            return
        }

        val (imageResId, descriptionText) = when (Pair(selectedColor, selectedCategory)) {
            Pair("Red", "Fruit") -> Pair(R.drawable.red_fruit, "Raspberry")
            Pair("Red", "Cartoon") -> Pair(R.drawable.red_cartoon, "Anger")
            Pair("Red", "Drink") -> Pair(R.drawable.red_drink, "Shirley Temple")
            Pair("Blue", "Fruit") -> Pair(R.drawable.blue_fruit, "Saskatoon")
            Pair("Blue", "Cartoon") -> Pair(R.drawable.blue_cartoon, "Stitch")
            Pair("Blue", "Drink") -> Pair(R.drawable.blue_drink, "Blue Lagoon")
            Pair("Green", "Fruit") -> Pair(R.drawable.green_fruit, "Pomelo")
            Pair("Green", "Cartoon") -> Pair(R.drawable.green_cartoon, "Shrek")
            Pair("Green", "Drink") -> Pair(R.drawable.green_drink, "Matcha Latte")
            else -> Pair(R.drawable.null_image, "Unknown Combination")
        }

        resultImage.setImageResource(imageResId)
        resultText.text = descriptionText
    }
}

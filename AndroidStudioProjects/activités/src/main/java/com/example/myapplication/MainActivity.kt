package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var textView: TextView
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickButton = findViewById(R.id.btn_click_me)
        textView = findViewById(R.id.textView1)
        clickButton.setOnClickListener{
            Toast.makeText(baseContext, "tu m'as cliqué", Toast.LENGTH_LONG).show()
            clickButton.setOnClickListener {
                nbClick++
                val newText = "vous avez cliquez $nbClick"
                textView.text = newText
                if (nbClick>=6) {
                    val nText ="vous avez atteindre le nombre de clique"
                    textView.text = nText
                    clickButton.isEnabled = false

                }
            }
        }

    }

}
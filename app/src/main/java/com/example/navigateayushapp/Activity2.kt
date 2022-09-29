package com.example.navigateayushapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val LoginButton =findViewById<Button>(R.id.loginButton)
        val skipButton =findViewById<Button>(R.id.skipButton)

        LoginButton.setOnClickListener{
            val intent =Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        skipButton.setOnClickListener{
            val intent =Intent(this,HomePage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
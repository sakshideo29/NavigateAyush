package com.example.navigateayushapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= Firebase.auth

        val currentUser =auth.currentUser

        if(currentUser!=null){
            Handler().postDelayed({
                startActivity(Intent(this,HomePage::class.java))
                finish()
            }, 2000)
        }
        else{
            Handler().postDelayed({
                startActivity(Intent(this, Activity2::class.java))
                finish()
            }, 2000)
        }
    }
}

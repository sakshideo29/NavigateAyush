package com.example.navigateayushapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_action.*

class Action : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        ViewAll.setOnClickListener {
            val intent=Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }

        ViewNearby.setOnClickListener {
            val intent=Intent(this,AllHospitalsMaps::class.java)
            startActivity(intent)
        }
    }
}


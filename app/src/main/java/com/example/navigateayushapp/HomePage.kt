package com.example.navigateayushapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        Ayurvedatext.setOnClickListener{
            SaveData.Which=1
            val intent =Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }
        Yogatext.setOnClickListener{
            SaveData.Which=2
            val intent =Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }
        Unanitext.setOnClickListener{
            SaveData.Which=3
            val intent =Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }
        Siddhatext.setOnClickListener{
            SaveData.Which=4
            val intent =Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }
        Homotext.setOnClickListener{
            SaveData.Which=5
            val intent =Intent(this,HospitalListPage::class.java)
            startActivity(intent)
        }
    }
}


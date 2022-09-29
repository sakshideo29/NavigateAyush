package com.example.navigateayushapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_know_more.*

class KnowMore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_know_more)
        KWTopText.text=SaveData.HospitalName
        KWHospitalName.text=SaveData.HospitalName
        KWHospitalTime.text=SaveData.HospitalTime
        KWInpatients.text=SaveData.Inpatients
        KWOutpatients.text=SaveData.Outpatients
        KWPractioners.text=SaveData.Practioners
        KWBeds.text=SaveData.Beds
        ImgLocation.setOnClickListener{
            val intent=Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
    }
}

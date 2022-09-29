package com.example.navigateayushapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_hospital_list_page.*
import kotlinx.android.synthetic.main.activity_hospital_list_page.view.*
import kotlinx.android.synthetic.main.activity_know_more.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HospitalListPage : AppCompatActivity() ,IPostAdapter{

    var database: DatabaseReference? = null
    var myAdapter: MyAdapter? = null
    var list: ArrayList<Hospitals>? = null
    var SearchList :ArrayList<Hospitals>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_list_page)

        if(SaveData.Which==1){
            Try.text="Ayurveda Hospitals"
            database = FirebaseDatabase.getInstance().getReference("AyurvedaHospitals")
        }
        if(SaveData.Which==2){
            Try.text="Yoga Hospitals"
            database = FirebaseDatabase.getInstance().getReference("YogaHospitals")
        }
        if(SaveData.Which==3){
            Try.text="Unani Hospitals"
            database = FirebaseDatabase.getInstance().getReference("UnaniHospitals")
        }
        if(SaveData.Which==4){
            Try.text="Siddha Hospitals"
            database = FirebaseDatabase.getInstance().getReference("SiddhaHospitals")
        }
        if(SaveData.Which==5){
            Try.text="Homeopathy Hospitals"
            Try.textSize= 30.0F
            database = FirebaseDatabase.getInstance().getReference("HomoHospitals")
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        list = ArrayList()
        myAdapter=MyAdapter(this, list!!,this)
        recyclerView.adapter = myAdapter

        GlobalScope.launch {
            database!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children) {
                        val hospital:Hospitals? = dataSnapshot.getValue(Hospitals::class.java)
                        if (hospital != null) {
                            list!!.add(hospital)
                        }
                    }
                    myAdapter!!.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }

        ImgAllMaps.setOnClickListener {
            SaveData.Maplist=list
            val intent=Intent(this,AllHospitalsMaps::class.java)
            startActivity(intent)
        }

        searchView.isSubmitButtonEnabled=true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(s: String): Boolean {
                val filteredList: ArrayList<Hospitals>? = ArrayList()
                for (item in list!!) {
                    if (item.hospitalName.toLowerCase().contains(s.toLowerCase())) {
                        filteredList?.add(item)
                    }
                }
                if (filteredList != null) {
                    myAdapter?.filterList(filteredList)
                }
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                val filteredList: ArrayList<Hospitals>? = ArrayList()
                for (item in list!!) {
                    if (item.hospitalName.toLowerCase().contains(s.toLowerCase())) {
                        filteredList?.add(item)
                    }
                }
                if (filteredList != null) {
                    myAdapter?.filterList(filteredList)
                }
                return true
            }
        })

    }

    override fun OpenPage( ) {
        val intent= Intent(this,KnowMore::class.java)
        startActivity(intent)
    }

    override fun OpenMaps() {
        val intent=Intent(this,MapsActivity::class.java)
        startActivity(intent)
    }


    private fun filter(text: String) {
        val filteredList: ArrayList<Hospitals>? = ArrayList()
        for (item in list!!) {
            if (item.hospitalName.toLowerCase().contains(text.toLowerCase())) {
                filteredList?.add(item)
            }
        }
        if (filteredList != null) {
            myAdapter?.filterList(filteredList)
        }
    }
}






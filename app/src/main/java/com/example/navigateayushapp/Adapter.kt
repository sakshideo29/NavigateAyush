package com.example.navigateayushapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hospital_box.view.*
import java.util.ArrayList


class MyAdapter(var context: Context, list: ArrayList<Hospitals>,val listener: IPostAdapter) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var list: ArrayList<Hospitals>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.hospital_box, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hospital: Hospitals = list[position]

        holder.Name.setText(hospital.hospitalName)
        holder.Time.setText(hospital.hospitalTime)
        holder.button.setOnClickListener{
            SaveData.HospitalName=hospital.hospitalName
            SaveData.HospitalTime=hospital.hospitalTime
            SaveData.Beds=hospital.noBeds
            SaveData.Inpatients=hospital.noInPt
            SaveData.Outpatients=hospital.noOutPt
            SaveData.Practioners=hospital.noPrt
            SaveData.Lat=hospital.lat.toDouble()
            SaveData.Lan=hospital.long.toDouble()
            listener.OpenPage()
        }
        holder.button2.setOnClickListener{
            SaveData.Lat=hospital.lat.toDouble()
            SaveData.Lan=hospital.long.toDouble()
            SaveData.HospitalName=hospital.hospitalName
            listener.OpenMaps()
        }
        if(hospital.hospitalStatus){
            holder.status.setText("OPEN")
            holder.image.setImageResource(R.drawable.img_19)

        }
        else{
            holder.status.setText("CLOSE")
            holder.image.setImageResource(R.drawable.img_20)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var Time: TextView
        var status :TextView
        var button :Button
        var button2 :Button
        var image :ImageView

        init {
            Name = itemView.findViewById(R.id.HospitalName)
            Time = itemView.findViewById(R.id.HospitalTime)
            status=itemView.findViewById(R.id.OpenCloseText)
            button=itemView.findViewById(R.id.ButtonKnowMore)
            button2=itemView.findViewById(R.id.ButtonLocation)
            image=itemView.findViewById(R.id.OpenCloseImage)
        }
    }
    init {
        this.list = list
    }

    fun filterList(filteredList: ArrayList<Hospitals>) {
        list = filteredList
        notifyDataSetChanged()
    }
}

interface IPostAdapter {
    fun OpenPage( )
    fun OpenMaps()
}


package com.govi.shayarilover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.govi.shayarilover.Adapter.AllShayariAdapter
import com.govi.shayarilover.Model.ShayariModel
import com.govi.shayarilover.databinding.ActivityAllShayariBinding

class AllShayariActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayariBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityAllShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name =  intent.getStringExtra("name")
        val id =  intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        binding.catagoryName.text = name.toString()

        db.collection("Shayari").document(id!!).collection("all").addSnapshotListener { value, error ->

            val shayariList = arrayListOf<ShayariModel>()
            val data = value?.toObjects(ShayariModel::class.java)
            shayariList.addAll(data!!)


            binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
            binding.rcvAllShayari.adapter = AllShayariAdapter(this,shayariList)

        }

    }
}
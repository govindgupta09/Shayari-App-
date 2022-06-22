package com.govi.shayarilover.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govi.shayarilover.AllShayariActivity
import com.govi.shayarilover.MainActivity
import com.govi.shayarilover.Model.CategoryModel
import com.govi.shayarilover.databinding.ItemCategoryBinding

class CategoryAdapter(
    val mainActivity: MainActivity,
    val list: ArrayList<CategoryModel>
) : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

val colorList = arrayListOf<String>("#55E6C1","#1B9CFC","#D6A2E8","#FC427B","#F97F51")

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position % 5 == 0) {
            holder.binding.itemText.setBackgroundColor(Color.parseColor((colorList[0])))
        }else if(position%5==1){
            holder.binding.itemText.setBackgroundColor(Color.parseColor((colorList[1])))
        }else if(position%5==2){
            holder.binding.itemText.setBackgroundColor(Color.parseColor((colorList[2])))
        }else if(position%5==3){
            holder.binding.itemText.setBackgroundColor(Color.parseColor((colorList[3])))
        }else if(position%5==4){
            holder.binding.itemText.setBackgroundColor(Color.parseColor((colorList[4])))
        }

        holder.binding.itemText.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity, AllShayariActivity::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)

            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size

}
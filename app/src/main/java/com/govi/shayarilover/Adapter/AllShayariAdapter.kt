package com.govi.shayarilover.Adapter

import android.content.ActivityNotFoundException
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govi.shayarilover.AllShayariActivity
import com.govi.shayarilover.Model.ShayariModel
import com.govi.shayarilover.R
import com.govi.shayarilover.databinding.ItemShayariBinding
import android.content.ClipData
import android.content.ClipboardManager

import android.content.Context.CLIPBOARD_SERVICE

import androidx.core.content.ContextCompat.getSystemService
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.startActivity
import com.govi.shayarilover.BuildConfig
import java.lang.Exception


class AllShayariAdapter(
    val allShayariActivity: AllShayariActivity,
    val shayariList: ArrayList<ShayariModel>
) : RecyclerView.Adapter<AllShayariAdapter.ShaayriViewHolder>() {

    class ShaayriViewHolder(val binding: ItemShayariBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShaayriViewHolder {
        return ShaayriViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShaayriViewHolder, position: Int) {

        if (position % 5 == 0) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient)
        }else if(position%5==1){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_1)
        }else if(position%5==2){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_2)
        }else if(position%5==3){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_3)
        }else if(position%5==4){
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradient_4)
        }


        holder.binding.itemShayari.text = shayariList[position].data.toString()
        holder.binding.btnShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${shayariList[position].data}\n\n"
                shareMessage =
                    """
                    ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                    
                    
                    """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
            Toast.makeText(allShayariActivity, "Shayari shared successfully ", Toast.LENGTH_SHORT).show()

        }
        holder.binding.btnCopy.setOnClickListener {
            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayariList[position].data.toString())
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(allShayariActivity, "Shayari copied successfully ", Toast.LENGTH_SHORT).show()

        }
        holder.binding.btnWhatsapp.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayariList[position].data.toString())
            try {
                allShayariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {

            }
        }
    }

    override fun getItemCount() = shayariList.size
}
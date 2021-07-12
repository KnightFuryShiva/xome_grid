package com.example.xomegrid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xomegrid.R
import com.example.xomegrid.model.Photo
import java.lang.StringBuilder


class RecyclerViewAdapter(private val onSuggestionSelected: (Any) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var photoList = ArrayList<Photo>()
    private lateinit var context: Context

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.img_view)
    }

    fun refresh(photo: List<Photo>) {
        photoList = photo as ArrayList<Photo>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_row_view, viewGroup, false)
        context = viewGroup.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        if (this::context.isInitialized)
            Glide.with(context).load(getImageURL(photoList[i])).into(viewHolder.imgView);
    }

    private fun getImageURL(photo: Photo): String {
        return "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}
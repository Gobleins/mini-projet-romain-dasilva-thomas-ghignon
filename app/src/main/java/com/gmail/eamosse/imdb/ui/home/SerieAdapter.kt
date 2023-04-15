package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Serie
import com.gmail.eamosse.idbdata.utils.ItemList
import com.gmail.eamosse.imdb.databinding.LargeListItemBinding

class SerieAdapter(private val items: List<Serie>, private val OnMovieClick: (ItemList) -> Unit) :
    RecyclerView.Adapter<SerieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: LargeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemList) {
            binding.item = item
            binding.largeListItemImg.load("https://image.tmdb.org/t/p/w500${item.image}") {
                crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(25f))
            }
            binding.root.setOnClickListener {
                OnMovieClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(LargeListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
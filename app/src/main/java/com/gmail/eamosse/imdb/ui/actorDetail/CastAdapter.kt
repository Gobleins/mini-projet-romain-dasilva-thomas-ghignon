package com.gmail.eamosse.imdb.ui.actorDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.idbdata.api.response.Cast
import com.gmail.eamosse.imdb.databinding.LargeCastListItemBinding

class CastAdapter(private val items: List<Cast>, private val OnMovieClick: (Cast) -> Unit) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: LargeCastListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cast) {
            binding.item = item
            binding.largeListItemImg.load("https://image.tmdb.org/t/p/w500${item.posterPath}") {
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
        return ViewHolder(LargeCastListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
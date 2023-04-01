package com.gmail.eamosse.imdb.ui.home

import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.utils.ItemList
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.SquareListItemBinding
import com.gmail.eamosse.imdb.ui.movieDetail.MovieDetailFragment

class ActorAdapter(private val items: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    val matrix = ColorMatrix().apply {
        setSaturation(1f) // make the image grayscale
        setScale(0.6f, 0.6f, 0.6f, 1f) // apply 0.2 opacity to the image
    }
    val filter: ColorFilter = ColorMatrixColorFilter(matrix)

    inner class ViewHolder(private val binding: SquareListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemList) {
            binding.item = item
            binding.squareListItemImg.load("https://image.tmdb.org/t/p/w500${item.image}") {
                crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(25f))
                listener(
                    onSuccess = { _, result ->
                        result.drawable.colorFilter = PorterDuffColorFilter(0x99000000.toInt(), PorterDuff.Mode.SRC_ATOP)
                        result.drawable.colorFilter = filter
                        binding.squareListItemImg.setImageDrawable(result.drawable)
                    }
                )
            }
//            binding.squareListItemImg.setOnClickListener {
//                val context = binding.squareListItemImg.context
//                val fragmentManager = (context as AppCompatActivity).supportFragmentManager
//                val transaction = fragmentManager.beginTransaction()
//                transaction.replace(R.id.container, MovieDetailFragment.newInstance(item.id))
//                transaction.addToBackStack(null)
//                transaction.commit()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(SquareListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
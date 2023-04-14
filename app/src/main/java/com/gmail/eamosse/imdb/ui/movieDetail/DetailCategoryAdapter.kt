package com.gmail.eamosse.imdb.ui.movieDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.utils.ItemList
import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding

class DetailCategoryAdapter(private val items: List<Category>, private val OnCategoryClick: (Category) -> Unit) :
    RecyclerView.Adapter<DetailCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.item = item
            binding.root.setOnClickListener {
                OnCategoryClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CategoryListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
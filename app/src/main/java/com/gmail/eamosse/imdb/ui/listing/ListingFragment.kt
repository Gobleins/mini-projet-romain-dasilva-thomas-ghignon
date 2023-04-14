package com.gmail.eamosse.imdb.ui.listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentListingBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.ui.home.MovieAdapter

@AndroidEntryPoint
class ListingFragment : Fragment() {

    private val args: ListingFragmentArgs by navArgs()
    private val viewModel: ListingViewModel by viewModels()
    private lateinit var binding: FragmentListingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            viewModel.getCategory(args.category.toInt())
            category.observe(viewLifecycleOwner, Observer { category ->
                viewModel.getMoviesByCategory(category)
                movies.observe(viewLifecycleOwner, Observer { movie ->
                    binding.listingRecyclerview.adapter = MovieAdapter(movie) {
                        findNavController().navigate(
                            ListingFragmentDirections.actionListingFragmentToMovieDetailFragment(it.identifier.toString())
                        )
                    }
                })
            })
        }
    }
}


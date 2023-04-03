package com.gmail.eamosse.imdb.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentListingBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
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
            viewModel.getMoviesByCategory(args.category)
            movies.observe(viewLifecycleOwner, Observer { movie ->
                binding.listingRecyclerview.adapter = MovieAdapter(movie) {
                    findNavController().navigate(
                        ListingFragmentDirections.actionListingFragmentToMovieDetailFragment(it.identifier.toString())
                    )
                }
            })
        }
    }
}


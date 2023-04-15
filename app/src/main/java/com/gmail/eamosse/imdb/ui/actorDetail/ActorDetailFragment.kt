package com.gmail.eamosse.imdb.ui.actorDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentActorDetailBinding
import com.gmail.eamosse.imdb.databinding.FragmentMovieDetailBinding
import com.gmail.eamosse.imdb.ui.home.ActorAdapter
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.home.MovieAdapter
import com.gmail.eamosse.imdb.ui.movieDetail.MovieDetailViewModel
import com.gmail.eamosse.imdb.utils.FadingImageView
import com.gmail.eamosse.imdb.utils.FirstItemMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActorDetailFragment : Fragment() {

    private val args: ActorDetailFragmentArgs by navArgs()
    private val viewModel: ActorDetailViewModel by viewModels()
    private lateinit var binding: FragmentActorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            viewModel.getDetailActor(args.actor.toInt())

            actor.observe(viewLifecycleOwner, Observer { it ->

                binding.item = it
                binding.actorDetailMovieList.adapter = MovieAdapter(it.movies) {
                    findNavController().navigate(
                        ActorDetailFragmentDirections.actionActorDetailFragmentToMovieDetailFragment(
                            it.identifier.toString()
                        )
                    )
                }

                getDetailPersonMovies(args.actor.toInt())
                movies.observe(viewLifecycleOwner, Observer { it ->
                    binding.actorDetailMovieList.adapter = CastAdapter(it) {
                        findNavController().navigate(
                            ActorDetailFragmentDirections.actionActorDetailFragmentToMovieDetailFragment(
                                it.id.toString()
                            )
                        )
                    }
                })


                val fadeImage = view.findViewById<FadingImageView>(R.id.actor_detail_header)
                fadeImage.load("https://image.tmdb.org/t/p/w500${it.image}") {
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(25f))
                }
                fadeImage.setEdgeLength(100)
                fadeImage.setFadeBottom(true)
            })
        }

//        val recyclerViewActor = view.findViewById<RecyclerView>(R.id.detail_actor_list)
//        val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.detail_category_list)
//        recyclerViewActor.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
//        recyclerViewCategory.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
    }
}


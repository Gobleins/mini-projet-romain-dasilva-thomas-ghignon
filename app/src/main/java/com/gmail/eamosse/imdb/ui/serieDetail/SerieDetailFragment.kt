package com.gmail.eamosse.imdb.ui.serieDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentMovieDetailBinding
import com.gmail.eamosse.imdb.databinding.FragmentSerieDetailBinding
import com.gmail.eamosse.imdb.ui.home.ActorAdapter
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.movieDetail.MovieDetailViewModel
import com.gmail.eamosse.imdb.utils.FadingImageView
import com.gmail.eamosse.imdb.utils.FirstItemMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerieDetailFragment : Fragment() {

    private val args: SerieDetailFragmentArgs by navArgs()
    private val viewModel: SerieDetailViewModel by viewModels()
    private lateinit var binding: FragmentSerieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSerieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            viewModel.getDetailSerie(args.myId.toInt())
            serie.observe(
                viewLifecycleOwner,
                Observer {

                    binding.item = it

//                    Log.d("TAG", "onViewCreated: ${it.seasons}")
//
//                    it.seasons.forEachIndexed { index, season ->
//                        val textViewId = resources.getIdentifier("detail_episode_list_title_${index+1}", "id", requireContext().packageName)
//                        Log.d("TAG", "onViewCreated: $textViewId")
//                        if (textViewId != 0) {
//                            val title = view.findViewById<TextView>(textViewId)
//                            title.text = "Season ${season.season_number} : ${season.name} (${season.episodes.size} episodes)"
//                        }
//                    }

                    val fadeImage = view.findViewById<FadingImageView>(R.id.header_blur_background)
                    fadeImage.load("https://image.tmdb.org/t/p/w500${it.backdrop_path}") {
                        crossfade(true)
                        crossfade(500)
                        transformations(RoundedCornersTransformation(25f))
                    }
                    fadeImage.setEdgeLength(100)
                    fadeImage.setFadeBottom(true)

                    val tag_vote = view.findViewById<TextView>(R.id.detail_tag_vote_average)
                    val tag_popularity = view.findViewById<TextView>(R.id.detail_tag_popularity)

                    tag_vote.text = " " + it.vote_average.toString() + "/10"
                    tag_popularity.text = " " + it.popularity.toString() + " votes"


                    viewModel.error.observe(viewLifecycleOwner, Observer {
                        // Handle error
                    })
                })


        }

//        val recyclerViewActor = view.findViewById<RecyclerView>(R.id.detail_actor_list)
//        val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.detail_category_list)
//        recyclerViewActor.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
//        recyclerViewCategory.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
    }
}


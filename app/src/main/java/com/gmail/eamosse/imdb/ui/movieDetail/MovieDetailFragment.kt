package com.gmail.eamosse.imdb.ui.movieDetail

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
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentMovieDetailBinding
import com.gmail.eamosse.imdb.ui.home.ActorAdapter
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.utils.FadingImageView
import com.gmail.eamosse.imdb.utils.FirstItemMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            viewModel.getDetailMovie(args.myId.toInt())
            movie.observe(
                viewLifecycleOwner,
                Observer {

                    binding.item = it
                    binding.detailActorList.adapter = ActorAdapter(it.actors, {})
                    binding.detailCategoryList.adapter = CategoryAdapter(it.category, {})

                    val fadeImage = view.findViewById<FadingImageView>(R.id.header_blur_background)
                    fadeImage.load("https://image.tmdb.org/t/p/w500${it.backdrop_path}") {
                        crossfade(true)
                        crossfade(500)
                        transformations(RoundedCornersTransformation(25f))
                    }
                    fadeImage.setEdgeLength(100)
                    fadeImage.setFadeBottom(true)

                    val tag_date = view.findViewById<TextView>(R.id.detail_tag_release_date)
                    val tag_vote = view.findViewById<TextView>(R.id.detail_tag_vote_average)
                    val tag_popularity = view.findViewById<TextView>(R.id.detail_tag_popularity)

                    tag_date.text = " " + it.release_date.toString()
                    tag_vote.text = " " + it.vote_average.toString() + "/10"
                    tag_popularity.text = " " + it.popularity.toString() + " votes"



//                    binding.detailVideo.webViewClient = object : WebViewClient() {
//                        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                            return false
//                        }
//                    }
//                    val ws: WebSettings = binding.detailVideo.settings
//                    ws.javaScriptEnabled = true
//
//                    val movieUrl = "Y5hcd7-5EmM"
//                    if (true) {
////                        binding.detailVideo.loadUrl("https://www.youtube.com/embed/${it.videos?.results?.firstOrNull()?.key}")
////                        val videoStr = "<html><body style=\"margin:0;padding:0; position: relative; padding-bottom: 56.25%; background-color: black\" style='margin:0;padding:0;'><iframe style=\"position: absolute; top: 0; left: 0; width: 100%; height: 100%;\" src=\"https://www.youtube.com/embed/${movieUrl}\" title=\"YouTube video player\" frameborder=\"0\" allow=\"encrypted-media; gyroscope; picture-in-picture;\" allowfullscreen></iframe></body></html>"
////                        binding.detailVideo.loadData(videoStr, "text/html", "utf-8")
//                    } else {
//                        binding.detailVideo.visibility = View.GONE
//                    }

                    viewModel.error.observe(viewLifecycleOwner, Observer {
                        // Handle error
                    })
                })
        }

        val recyclerViewActor = view.findViewById<RecyclerView>(R.id.detail_actor_list)
        val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.detail_category_list)
        recyclerViewActor.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
        recyclerViewCategory.addItemDecoration(FirstItemMarginDecoration(resources.getDimensionPixelSize(R.dimen.my_margin_size_detail)))
    }
}


package com.gmail.eamosse.imdb.ui.movieDetail

import android.os.Bundle
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.ui.home.HomeViewModel

class MovieDetailFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                getMovie(movieId)
            })
        }


    }
    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_MOVIE_ID, movieId)
            }
        }
    }
}

//        val movieId = arguments?.getInt("movieId") ?: throw IllegalArgumentException("Missing movieId argument")
//        viewModel.getMovie(movieId)
//
//        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
//            // Update UI with movie details
//            // Example:
//            // movieTitleTextView.text = movie.title
//
//    val movieUrl = "X-iEq8hWd6k"
//    val videoStr = "<iframe width=\"100%\" height=\"auto\" src=\"https://www.youtube.com/embed/${movieUrl}\" title=\"YouTube video player\" frameborder=\"0\" allow=\"encrypted-media; gyroscope; picture-in-picture;\" allowfullscreen></iframe>"
//
//    binding.webview.webViewClient = object : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            return false
//        }
//    }
//
//    val ws: WebSettings = binding.webview.settings
//    ws.javaScriptEnabled = true
//
//    binding.webview.loadData(videoStr, "text/html", "utf-8")
//
//        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
//            // Handle error
//        })
//    }
//    }
//}


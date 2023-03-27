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

class MovieDetailFragment : Fragment() {

    //TODO: Ajouter le ViewModel

//    private val viewModel: MovieDetailViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val movieId = arguments?.getInt("movieId") ?: throw IllegalArgumentException("Missing movieId argument")
//        viewModel.getMovie(movieId)
//
//        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
//            // Update UI with movie details
//            // Example:
//            // movieTitleTextView.text = movie.title
//
//val movieUrl = "X-iEq8hWd6k"
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
}


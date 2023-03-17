package com.gmail.eamosse.imdb.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.ui.movieDetail.MovieDetailViewModel

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: MovieDetailViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
                ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        profileViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
//        })
        return root
    }
}

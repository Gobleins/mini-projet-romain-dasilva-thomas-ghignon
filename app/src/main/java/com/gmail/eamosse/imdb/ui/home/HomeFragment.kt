package com.gmail.eamosse.imdb.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentHomeBinding
import com.gmail.eamosse.imdb.utils.FadingImageView
import com.gmail.eamosse.imdb.utils.FirstItemMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(homeViewModel) {

            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories$
                getPopularMovies()
                getCategories()
                getPopularActors()
                getHighlightMovie()
            })

            categories.observe(viewLifecycleOwner, Observer { categories ->
                binding.categoryList.adapter = CategoryAdapter(categories) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToListingFragment(it.id.toString())
                    )
                }
            })

            movies.observe(viewLifecycleOwner, Observer { movies ->
                binding.homeMoviesList.adapter = MovieAdapter(movies) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it.identifier.toString())
                    )
                }
            })

            actors.observe(viewLifecycleOwner, Observer {
                binding.homeActorList.adapter = ActorAdapter(it) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToActorDetailFragment(it.identifier.toString())
                    )
                }
            })

            highlightMovie.observe(viewLifecycleOwner, Observer { movie ->
                Log.d("TAG", "highlightMovie inside homeFragment: $movie")
                binding.homeHeaderImage.load("https://image.tmdb.org/t/p/w500${movie.poster_path}") {
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(35f))
                }

                binding.homeHeaderImage.setOnClickListener {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.identifier.toString())
                    )
                }
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.category_list)
        val recyclerViewMovies = view.findViewById<RecyclerView>(R.id.home_movies_list)
        val recyclerViewActor = view.findViewById<RecyclerView>(R.id.home_actor_list)
        val margin = resources.getDimensionPixelSize(R.dimen.my_margin_size)
        recyclerView.addItemDecoration(FirstItemMarginDecoration(margin))
        recyclerViewMovies.addItemDecoration(FirstItemMarginDecoration(margin))
        recyclerViewActor.addItemDecoration(FirstItemMarginDecoration(margin))
    }

    fun blurBitmap(context: Context, drawableId: Int, iterations: Int): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)
        val inputBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width / 2, bitmap.height / 2, false)

        val rs = RenderScript.create(context)
        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val input = Allocation.createFromBitmap(rs, inputBitmap)
        val output = Allocation.createTyped(rs, input.type)

        var blurredBitmap = inputBitmap
        repeat(iterations) {
            blurScript.setRadius(10f)
            blurScript.setInput(Allocation.createFromBitmap(rs, blurredBitmap))
            blurScript.forEach(output)
            output.copyTo(blurredBitmap)
        }

        return blurredBitmap
    }
}

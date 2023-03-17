package com.gmail.eamosse.imdb.ui.home

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context
import android.graphics.BitmapFactory
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.gmail.eamosse.imdb.utils.FadingImageView

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private var bg_image: ImageView? = null
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

//        bg_image = view.findViewById(R.id.header_blur_background)
//        val blurredBitmap = blurBitmap(requireContext(), R.mipmap.ic_avatar_foreground, 20)
//        bg_image!!.setImageBitmap(blurredBitmap)


        val fadeImage = view.findViewById<FadingImageView>(R.id.header_blur_background)
        val blurredBitmap = blurBitmap(requireContext(), R.mipmap.ic_avatar_foreground, 10)
        fadeImage.setImageBitmap(blurredBitmap)
        fadeImage.setEdgeLength(100)
        fadeImage.setFadeBottom(true)


        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                getCategories()
            })

            categories.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = CategoryAdapter(it)
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
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

package com.gmail.eamosse.imdb.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
import android.graphics.Canvas
import android.graphics.Paint
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.squareup.picasso.Transformation

class BlurTransformation(private val context: Context, private val radius: Int) : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val blurredBitmap = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(blurredBitmap)
        canvas.scale(0.1f, 0.1f)
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        val rs = RenderScript.create(context)
        val input = Allocation.createFromBitmap(rs, source, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
        val output = Allocation.createTyped(rs, input.type)
        val blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        blur.setInput(input)
        blur.setRadius(radius.toFloat())
        blur.forEach(output)
        output.copyTo(blurredBitmap)
        source.recycle()
        rs.destroy()
        return blurredBitmap
    }

    override fun key(): String {
        return "blur"
    }
}

package com.emanuelgalvao.swipetocatch.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.ListResourceBundle

object BitmapUtils {

    fun getBitmap(resources: Resources, id: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inScaled = false
        return BitmapFactory.decodeResource(resources, id, options)
    }
}
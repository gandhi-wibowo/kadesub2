package com.dizcoding.kadesubmission2.tools

import android.widget.ImageView
import com.squareup.picasso.Picasso

class Utils {

    fun picShow(img: String, imgView: ImageView) {
        Picasso
            .get()
            .load(img)
            .into(imgView)
    }
}
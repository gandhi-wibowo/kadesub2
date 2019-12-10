package com.dizcoding.kadesubmission2.layout.activity

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dizcoding.kadesubmission2.model.Leagues
import com.dizcoding.kadesubmission2.tools.Utils
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LigaDetailLayout(val leagues: Leagues) : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        scrollView {
            lparams(matchParent, matchParent)

            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(5)
                }
                radius = dip(5).toFloat()

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    lparams(width = matchParent, height = matchParent)

                    val logo: ImageView = imageView {
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }.lparams(width = matchParent, height = wrapContent)

                    val judul: TextView = textView {
                        gravity = Gravity.CENTER_HORIZONTAL
                        textColor = Color.BLACK
                        textSize = dip(12).toFloat()
                    }.lparams(width = matchParent, height = matchParent)
                    val desc: TextView = textView {
                        textColor = Color.BLACK
                        padding = dip(5)
                    }.lparams(width = matchParent, height = matchParent)

                    Utils().picShow("file:///android_asset/" + leagues.logo, logo)
                    judul.text = leagues.name
                    desc.text = leagues.description
                }
            }

        }

    }

}
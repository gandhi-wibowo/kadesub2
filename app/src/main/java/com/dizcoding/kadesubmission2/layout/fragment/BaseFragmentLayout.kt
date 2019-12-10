package com.dizcoding.kadesubmission2.layout.fragment

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.dizcoding.kadesubmission2.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class BaseFragmentLayout : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent) {
                orientation = LinearLayout.VERTICAL
            }

            linearLayout {
                lparams(width = matchParent, height = dip(200)) {
                }
                viewPager {
                    id = R.id.lgVp
                }.lparams(matchParent, matchParent)

            }
            linearLayout {
                lparams(width = matchParent, height = dip(45))
                tabLayout {
                    id = R.id.bfIdtbl
                    lparams(width = matchParent, height = matchParent)
                }
            }
            linearLayout {
                lparams(width = matchParent, height = matchParent) {
                }
                viewPager {
                    id = R.id.bfVp
                }.lparams(matchParent, matchParent)

            }

        }
    }

}
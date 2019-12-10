package com.dizcoding.kadesubmission2.layout.fragment

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.kadesubmission2.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class NextMatchFragmentLayout : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        recyclerView {
            id = R.id.nmFrv
            lparams(matchParent, matchParent)
            layoutManager = LinearLayoutManager(ctx, RecyclerView.VERTICAL, false)
        }
    }
}
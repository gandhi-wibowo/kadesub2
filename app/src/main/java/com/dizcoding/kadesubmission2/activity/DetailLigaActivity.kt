package com.dizcoding.kadesubmission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dizcoding.kadesubmission2.layout.activity.LigaDetailLayout
import com.dizcoding.kadesubmission2.model.Leagues
import org.jetbrains.anko.setContentView

class DetailLigaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.getBundleExtra("Bundle")
        val leagues: Leagues? = bundle.getParcelable("league")
        if (leagues != null) {
            LigaDetailLayout(leagues).setContentView(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

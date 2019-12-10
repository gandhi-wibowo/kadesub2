package com.dizcoding.kadesubmission2.layout.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.dizcoding.kadesubmission2.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchItemLayout : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        cardView {
            lparams(matchParent, dip(150)) {
                margin = dip(5)
                radius = dip(5).toFloat()

            }
            linearLayout {
                lparams(matchParent, matchParent) {
                    orientation = LinearLayout.VERTICAL
                }

                /*Header / Title*/
                linearLayout {
                    lparams(matchParent, matchParent) {
                        orientation = LinearLayout.VERTICAL
                        weight = .3.toFloat()
                        backgroundColor = Color.YELLOW
                    }
                    linearLayout {
                        lparams(matchParent, matchParent) {
                            weight = .1.toFloat()
                        }

                        textView {
                            id = R.id.tvEventName
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = dip(6).toFloat()
                        }.lparams(matchParent, matchParent)
                    }
                    linearLayout {
                        lparams(matchParent, matchParent) {
                            weight = .1.toFloat()
                        }

                        textView {
                            id = R.id.tvLigaName
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = dip(5).toFloat()
                        }.lparams(matchParent, matchParent)
                    }
                }
                /*Body*/
                linearLayout {
                    lparams(matchParent, matchParent) {
                        orientation = LinearLayout.HORIZONTAL
                        weight = .1.toFloat()
                        padding = dip(5)
                    }

                    /*Home*/
                    linearLayout {
                        lparams(matchParent, matchParent) {
                            orientation = LinearLayout.HORIZONTAL
                            weight = .1.toFloat()
                        }
                        /*Home Image*/
                        linearLayout {
                            lparams(matchParent, matchParent) {
                                weight = .1.toFloat()
                                orientation = LinearLayout.VERTICAL
                            }
                            linearLayout {
                                lparams(matchParent, matchParent) {
                                    weight = .1.toFloat()
                                }
                                imageView {
                                    id = R.id.ivHome
                                    scaleType = ImageView.ScaleType.FIT_CENTER
                                }.lparams(matchParent, matchParent)
                            }
                            linearLayout {
                                lparams(matchParent, wrapContent)
                                textView {
                                    id = R.id.tvHomeTeamName
                                    gravity = Gravity.CENTER
                                    textColor = Color.BLACK
                                    typeface = Typeface.DEFAULT_BOLD
                                    textSize = dip(5).toFloat()
                                }.lparams(matchParent, matchParent)
                            }

                        }
                        /*Home Score*/
                        linearLayout {
                            lparams(matchParent, matchParent) {
                                weight = .4.toFloat()
                                orientation = LinearLayout.VERTICAL
                            }
                            textView {
                                id = R.id.tvHomeScore

                                gravity = Gravity.CENTER
                                textColor = Color.BLACK
                                typeface = Typeface.DEFAULT_BOLD
                                textSize = dip(8).toFloat()

                            }.lparams(matchParent, matchParent)
                        }

                    }
                    /*Pemisah*/
                    linearLayout {
                        lparams(dip(20), matchParent) {
                            orientation = LinearLayout.VERTICAL
                        }
                        textView(":") {
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                            typeface = Typeface.DEFAULT_BOLD
                            textSize = dip(8).toFloat()

                        }.lparams(matchParent, matchParent)
                    }

                    /*Away*/
                    linearLayout {
                        lparams(matchParent, matchParent) {
                            orientation = LinearLayout.HORIZONTAL
                            weight = .1.toFloat()
                        }
                        /*Away Score*/
                        linearLayout {
                            lparams(matchParent, matchParent) {
                                weight = .4.toFloat()
                                orientation = LinearLayout.VERTICAL
                            }
                            textView {
                                id = R.id.tvAwayScore
                                gravity = Gravity.CENTER
                                textColor = Color.BLACK
                                typeface = Typeface.DEFAULT_BOLD
                                textSize = dip(8).toFloat()

                            }.lparams(matchParent, matchParent)
                        }
                        /*Away Image*/
                        linearLayout {
                            lparams(matchParent, matchParent) {
                                weight = .1.toFloat()
                                orientation = LinearLayout.VERTICAL
                            }

                            linearLayout {
                                lparams(matchParent, matchParent) {
                                    weight = .1.toFloat()
                                }
                                imageView {
                                    id = R.id.ivAway
                                    scaleType = ImageView.ScaleType.FIT_CENTER
                                }.lparams(matchParent, matchParent)
                            }
                            linearLayout {
                                lparams(matchParent, wrapContent)
                                textView {
                                    id = R.id.tvAwayTeamName
                                    gravity = Gravity.CENTER
                                    textColor = Color.BLACK
                                    typeface = Typeface.DEFAULT_BOLD
                                    textSize = dip(5).toFloat()
                                }.lparams(matchParent, matchParent)
                            }
                        }

                    }
                }


            }
        }


    }
}
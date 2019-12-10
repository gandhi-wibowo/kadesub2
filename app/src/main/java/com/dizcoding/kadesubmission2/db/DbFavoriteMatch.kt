package com.dizcoding.kadesubmission2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dizcoding.kadesubmission2.db.table.FavoriteMatch
import org.jetbrains.anko.db.*

class DbFavoriteMatch(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: DbFavoriteMatch? = null

        @Synchronized
        fun getInstance(ctx: Context): DbFavoriteMatch {
            if (instance == null) {
                instance = DbFavoriteMatch(ctx.applicationContext)
            }
            return instance as DbFavoriteMatch
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Ok, lets try make a database
        db.createTable(
            FavoriteMatch.TABLE_NAME, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.dateEvent to TEXT,
            FavoriteMatch.dateEventLocal to TEXT,
            FavoriteMatch.idAwayTeam to TEXT,
            FavoriteMatch.idEvent to TEXT + UNIQUE,
            FavoriteMatch.idHomeTeam to TEXT,
            FavoriteMatch.idLeague to TEXT,
            FavoriteMatch.idSoccerXML to TEXT,
            FavoriteMatch.intAwayScore to TEXT,
            FavoriteMatch.intAwayShots to TEXT,
            FavoriteMatch.intHomeScore to TEXT,
            FavoriteMatch.intHomeShots to TEXT,
            FavoriteMatch.intRound to TEXT,
            FavoriteMatch.intSpectators to TEXT,
            FavoriteMatch.strAwayFormation to TEXT,
            FavoriteMatch.strAwayGoalDetails to TEXT,
            FavoriteMatch.strAwayLineupDefense to TEXT,
            FavoriteMatch.strAwayLineupForward to TEXT,
            FavoriteMatch.strAwayLineupGoalkeeper to TEXT,
            FavoriteMatch.strAwayLineupMidfield to TEXT,
            FavoriteMatch.strAwayLineupSubstitutes to TEXT,
            FavoriteMatch.strAwayRedCards to TEXT,
            FavoriteMatch.strAwayTeam to TEXT,
            FavoriteMatch.strAwayYellowCards to TEXT,
            FavoriteMatch.strBanner to TEXT,
            FavoriteMatch.strCircuit to TEXT,
            FavoriteMatch.strCity to TEXT,
            FavoriteMatch.strCountry to TEXT,
            FavoriteMatch.strDate to TEXT,
            FavoriteMatch.strDescriptionEN to TEXT,
            FavoriteMatch.strEvent to TEXT,
            FavoriteMatch.strEventAlternate to TEXT,
            FavoriteMatch.strFanart to TEXT,
            FavoriteMatch.strFilename to TEXT,
            FavoriteMatch.strHomeFormation to TEXT,
            FavoriteMatch.strHomeGoalDetails to TEXT,
            FavoriteMatch.strHomeLineupDefense to TEXT,
            FavoriteMatch.strHomeLineupForward to TEXT,
            FavoriteMatch.strHomeLineupGoalkeeper to TEXT,
            FavoriteMatch.strHomeLineupMidfield to TEXT,
            FavoriteMatch.strHomeLineupSubstitutes to TEXT,
            FavoriteMatch.strHomeRedCards to TEXT,
            FavoriteMatch.strHomeTeam to TEXT,
            FavoriteMatch.strHomeYellowCards to TEXT,
            FavoriteMatch.strLeague to TEXT,
            FavoriteMatch.strLocked to TEXT,
            FavoriteMatch.strMap to TEXT,
            FavoriteMatch.strPoster to TEXT,
            FavoriteMatch.strResult to TEXT,
            FavoriteMatch.strSeason to TEXT,
            FavoriteMatch.strSport to TEXT,
            FavoriteMatch.strTVStation to TEXT,
            FavoriteMatch.strThumb to TEXT,
            FavoriteMatch.strTime to TEXT,
            FavoriteMatch.strTimeLocal to TEXT,
            FavoriteMatch.strTweet1 to TEXT,
            FavoriteMatch.strTweet2 to TEXT,
            FavoriteMatch.strTweet3 to TEXT,
            FavoriteMatch.strVideo to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_NAME, true)
    }
}

val Context.dbFavoriteMatch: DbFavoriteMatch get() = DbFavoriteMatch.getInstance(applicationContext)
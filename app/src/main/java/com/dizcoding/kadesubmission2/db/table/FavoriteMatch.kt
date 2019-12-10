package com.dizcoding.kadesubmission2.db.table

data class FavoriteMatch(
    val ID : Long?,
    val dateEvent  : String?,
    val dateEventLocal  : String?,
    val idAwayTeam  : String?,
    val idEvent  : String?,
    val idHomeTeam  : String?,
    val idLeague  : String?,
    val idSoccerXML  : String?,
    val intAwayScore  : String?,
    val intAwayShots  : String?,
    val intHomeScore  : String?,
    val intHomeShots  : String?,
    val intRound  : String?,
    val intSpectators  : String?,
    val strAwayFormation  : String?,
    val strAwayGoalDetails  : String?,
    val strAwayLineupDefense  : String?,
    val strAwayLineupForward  : String?,
    val strAwayLineupGoalkeeper  : String?,
    val strAwayLineupMidfield  : String?,
    val strAwayLineupSubstitutes  : String?,
    val strAwayRedCards  : String?,
    val strAwayTeam  : String?,
    val strAwayYellowCards  : String?,
    val strBanner  : String?,
    val strCircuit  : String?,
    val strCity  : String?,
    val strCountry  : String?,
    val strDate  : String?,
    val strDescriptionEN  : String?,
    val strEvent  : String?,
    val strEventAlternate : String?,
    val strFanart  : String?,
    val strFilename  : String?,
    val strHomeFormation  : String?,
    val strHomeGoalDetails  : String?,
    val strHomeLineupDefense  : String?,
    val strHomeLineupForward  : String?,
    val strHomeLineupGoalkeeper  : String?,
    val strHomeLineupMidfield  : String?,
    val strHomeLineupSubstitutes  : String?,
    val strHomeRedCards  : String?,
    val strHomeTeam  : String?,
    val strHomeYellowCards  : String?,
    val strLeague  : String?,
    val strLocked  : String?,
    val strMap  : String?,
    val strPoster  : String?,
    val strResult  : String?,
    val strSeason  : String?,
    val strSport  : String?,
    val strTVStation  : String?,
    val strThumb  : String?,
    val strTime  : String?,
    val strTimeLocal  : String?,
    val strTweet1  : String?,
    val strTweet2  : String?,
    val strTweet3  : String?,
    val strVideo  : String?
){
    companion object{
        const val TABLE_NAME: String = "favorite_match"
        const val ID: String = "ID_"
        const val dateEvent: String = "dateEvent"
        const val dateEventLocal: String = "dateEventLocal"
        const val idAwayTeam: String = "idAwayTeam"
        const val idEvent: String ="idEvent"
        const val idHomeTeam: String = "idHomeTeam"
        const val idLeague: String ="idLeague"
        const val idSoccerXML: String ="idSoccerXML"
        const val intAwayScore: String ="intAwayScore"
        const val intAwayShots: String ="intAwayShots"
        const val intHomeScore: String ="intHomeScore"
        const val intHomeShots: String ="intHomeShots"
        const val intRound: String ="intRound"
        const val intSpectators: String ="intSpectators"
        const val strAwayFormation: String ="strAwayFormation"
        const val strAwayGoalDetails: String = "strAwayGoalDetails"
        const val strAwayLineupDefense: String ="strAwayLineupDefense"
        const val strAwayLineupForward: String ="strAwayLineupForward"
        const val strAwayLineupGoalkeeper: String ="strAwayLineupGoalkeeper"
        const val strAwayLineupMidfield: String ="strAwayLineupMidfield"
        const val strAwayLineupSubstitutes: String ="strAwayLineupSubstitutes"
        const val strAwayRedCards: String ="strAwayRedCards"
        const val strAwayTeam: String ="strAwayTeam"
        const val strAwayYellowCards: String = "strAwayYellowCards"
        const val strBanner: String ="strBanner"
        const val strCircuit: String = "strCircuit"
        const val strCity: String ="strCity"
        const val strCountry: String = "strCountry"
        const val strDate: String ="strDate"
        const val strDescriptionEN: String ="strDescriptionEN"
        const val strEvent: String ="strEvent"
        const val strEventAlternate: String ="strEventAlternate"
        const val strFanart: String ="strFanart"
        const val strFilename: String ="strFilename"
        const val strHomeFormation: String ="strHomeFormation"
        const val strHomeGoalDetails: String = "strHomeGoalDetails"
        const val strHomeLineupDefense: String ="strHomeLineupDefense"
        const val strHomeLineupForward: String ="strHomeLineupForward"
        const val strHomeLineupGoalkeeper: String ="strHomeLineupGoalkeeper"
        const val strHomeLineupMidfield: String ="strHomeLineupMidfield"
        const val strHomeLineupSubstitutes: String ="strHomeLineupSubstitutes"
        const val strHomeRedCards: String ="strHomeRedCards"
        const val strHomeTeam: String ="strHomeTeam"
        const val strHomeYellowCards: String = "strHomeYellowCards"
        const val strLeague: String ="strLeague"
        const val strLocked: String ="strLocked"
        const val strMap: String = "strMap"
        const val strPoster: String ="strPoster"
        const val strResult: String ="strResult"
        const val strSeason: String ="strSeason"
        const val strSport: String ="strSport"
        const val strTVStation: String ="strTVStation"
        const val strThumb: String ="strThumb"
        const val strTime: String ="strTime"
        const val strTimeLocal: String ="strTimeLocal"
        const val strTweet1: String ="strTweet1"
        const val strTweet2: String ="strTweet2"
        const val strTweet3: String ="strTweet3"
        const val strVideo: String ="strVideo"
    }
}
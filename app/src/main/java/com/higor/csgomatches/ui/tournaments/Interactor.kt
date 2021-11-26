package com.higor.csgomatches.ui.tournaments

import com.higor.csgomatches.data.tournament.entity.Tournament

interface Interactor {

    fun showTournamentDetails(tournament: Tournament)
}

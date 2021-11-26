package com.higor.csgomatches.ui.tournaments

import com.higor.csgomatches.data.tournament.entity.Tournament
import com.higor.csgomatches.data.tournament.repository.TournamentsRepository
import com.higor.csgomatches.util.TournamentViewModel

class TournamentsItemViewModel(
    private val interactor: Interactor,
    repository: TournamentsRepository
): TournamentViewModel(repository) {

    fun clickTournamentItem(){
        val tournament: Tournament? = getTournament()

        tournament?.let{
            interactor.showTournamentDetails(it)
        }
    }
}
package com.higor.csgomatches.data.tournament.repository

import com.higor.csgomatches.data.tournament.entity.Tournament
import io.reactivex.Observable
import java.util.*

interface TournamentsRepository {

    fun getRunningTournaments(): Observable<List<Tournament>>

    fun getTournamentDetails(tournamentId: Int): Observable<Tournament>
}
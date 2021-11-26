package com.higor.csgomatches.util

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableField
import com.higor.csgomatches.data.tournament.entity.Tournament
import com.higor.csgomatches.data.tournament.repository.TournamentsRepository

open class TournamentViewModel(
    private val repository: TournamentsRepository,
) : BaseObservable() {

    private val observableTournament = ObservableField<Tournament>()

    init {
        observableTournament.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val tournament = observableTournament.get()

                tournament?.let{

                }
            }
        })
    }

    fun setTournament(tournament: Tournament) {
        observableTournament.set(tournament)
    }

    fun getTournament(): Tournament? {
        return observableTournament.get()
    }
}
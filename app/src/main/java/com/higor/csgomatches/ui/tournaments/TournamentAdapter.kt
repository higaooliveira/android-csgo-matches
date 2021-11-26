package com.higor.csgomatches.ui.tournaments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.higor.csgomatches.R
import com.higor.csgomatches.data.tournament.entity.Tournament
import com.higor.csgomatches.data.tournament.repository.TournamentsRepository
import com.higor.csgomatches.databinding.TournamentItem

class TournamentAdapter(
    private val interactor: Interactor,
    private val tournamentRepository: TournamentsRepository,
    private var tournaments: List<Tournament> = arrayListOf()
): RecyclerView.Adapter<TournamentViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setTournaments(tournaments: List<Tournament>) {
        this.tournaments = tournaments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val tournamentItem = DataBindingUtil.inflate<TournamentItem>(LayoutInflater.from(parent.context),
            R.layout.item_tournament_layout, parent, false)

        return TournamentViewHolder(tournamentItem)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val tournament = this.tournaments[position]

        val tournamentItemViewModel = TournamentsItemViewModel(this.interactor, this.tournamentRepository)

        tournamentItemViewModel.setTournament(tournament)

        holder.setTournamentItemViewModel(tournamentItemViewModel)
    }

    override fun getItemCount(): Int {
        return this.tournaments.size
    }
}

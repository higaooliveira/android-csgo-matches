package com.higor.csgomatches.ui.tournaments

import androidx.recyclerview.widget.RecyclerView
import com.higor.csgomatches.databinding.TournamentItem

class TournamentViewHolder(
    private val tournamentItem: TournamentItem
) : RecyclerView.ViewHolder(tournamentItem.root) {

    fun setTournamentItemViewModel(tournamentItemViewModel: TournamentsItemViewModel){
        tournamentItem.tournamentItemViewModel = tournamentItemViewModel
        tournamentItem.executePendingBindings()
    }
}

package com.higor.csgomatches.ui.tournaments

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.higor.csgomatches.data.tournament.entity.Tournament
import java.lang.IllegalStateException

class TournamentsListBinding {


    companion object {
        @BindingAdapter("bind:tournaments")
        fun setTournaments(recyclerView: RecyclerView, tournaments: List<Tournament>) {
            val adapter  = recyclerView.adapter

            if (adapter != null && adapter is TournamentAdapter) {
                adapter.setTournaments(tournaments)
            }else {
                throw IllegalStateException("RecyclerView either has no adapter set or the adapter isn't of type TournamentAdapter")
            }
        }
    }
}

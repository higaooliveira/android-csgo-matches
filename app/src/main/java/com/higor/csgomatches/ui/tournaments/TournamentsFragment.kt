package com.higor.csgomatches.ui.tournaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.higor.csgomatches.R
import com.higor.csgomatches.data.tournament.entity.Tournament
import com.higor.csgomatches.data.tournament.repository.TournamentsRepository
import com.higor.csgomatches.databinding.TournamentListBinding

class TournamentsFragment: Fragment(), Interactor {

    private lateinit var binding: TournamentListBinding

    private lateinit var tournamentsViewModel: TournamentsViewModel

    private lateinit var tournamentsRepository: TournamentsRepository
    private lateinit var tournamentAdapter: TournamentAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tournaments, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModels()
        setupToolbar()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        tournamentAdapter = TournamentAdapter(this, tournamentsRepository)

        binding.tournaments.layoutManager = GridLayoutManager(context, 2)
        binding.tournaments.adapter = tournamentAdapter
    }

    private fun setupToolbar() {
        binding.toolbar.setTitle(R.string.app_name)
    }

    private fun setupViewModels() {
        tournamentsViewModel = TournamentsViewModel(tournamentsRepository, requireContext())

        binding.tournamentsViewModel = tournamentsViewModel
    }

    override fun showTournamentDetails(tournament: Tournament) {
        Toast.makeText(context, tournament.toString(), Toast.LENGTH_SHORT).show()

        val fragmentTransaction = fragmentManager?.beginTransaction()

        //TODO
//        fragmentTransaction.replace(R.id.container, )
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    companion object {
        private val TAG = TournamentsFragment::class.java.name

        fun newInstance(): Fragment {
            return TournamentsFragment()
        }
    }


}
package com.ubaya.project_uts_160420147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.project_uts_160420147.databinding.FragmentGameListBinding
import com.ubaya.project_uts_160420147.viewmodel.GameListModel

class GameListFragment : Fragment() {
    private lateinit var gamesViewModel: GameListModel
    private lateinit var gamesListAdapter: GameListAdapter
    private lateinit var binding: FragmentGameListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesViewModel = ViewModelProvider(this).get(GameListModel::class.java)
        gamesListAdapter = GameListAdapter(listOf())

        binding.recView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gamesListAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            gamesViewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        setupFab()

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        gamesViewModel.refresh()
    }

    private fun setupFab() {
        binding.fabTambahGame.setOnClickListener {
            val action = GameListFragmentDirections.actionToTambahGame()
            Navigation.findNavController(it).navigate(action)
        }
    }


    private fun observeViewModel() {
        gamesViewModel.gameLD.observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                binding.recView.visibility = View.VISIBLE
                gamesListAdapter.updateGameList(it)
            }
        })

        gamesViewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            binding.txtError.visibility = if (isError) View.VISIBLE else View.GONE
        })

        gamesViewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE
                binding.txtError.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}
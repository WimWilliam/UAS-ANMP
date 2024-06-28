package com.ubaya.project_uts_160420147.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.ubaya.project_uts_160420147.databinding.FragmentGameDetailBinding
import com.ubaya.project_uts_160420147.model.Game
import com.ubaya.project_uts_160420147.model.News
import com.ubaya.project_uts_160420147.viewmodel.DetailViewModel
import com.ubaya.project_uts_160420147.viewmodel.SharedViewModel

class GameDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentGameDetailBinding
    private val newsAdapter = NewsAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = GameDetailFragmentArgs.fromBundle(requireArguments()).gameId
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewNews.adapter = newsAdapter


        val args: GameDetailFragmentArgs by navArgs()
        val gameId = args.gameId
        viewModel.fetch(gameId)
        viewModel.fetchNews(gameId)

        viewModel.gameLD.observe(viewLifecycleOwner, Observer { game ->
            game?.let {
                updateUI(game)
            }
        })

        viewModel.newsLD.observe(viewLifecycleOwner, Observer { news ->
            news?.let {
                Log.d("GameDetailFragment", "News loaded: $news")
                updateNews(news)
            }
        })

        binding.btnTambah.setOnClickListener {
            val action = GameDetailFragmentDirections.actionItemReadHistoryToCreateNewsFragment(gameId.toInt())
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun updateUI(game: Game) {
        binding.textViewTitle.text = game.title
        binding.textViewAuthor.text = game.developer
        Picasso.get().load(game.imageURL).into(binding.imageViewNews)
    }

    private fun updateNews(news: List<News>) {
        Log.d("GameDetailFragment", "Updating news with ${news.size} items")
        newsAdapter.updateNewsList(news)
    }
}

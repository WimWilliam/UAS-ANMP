package com.ubaya.project_uts_160420147.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.databinding.GamesCardItemsBinding
import com.ubaya.project_uts_160420147.model.Game


class GameListAdapter(private var games: List<Game>) :
    RecyclerView.Adapter<GameListAdapter.GameViewHolder>(),ButtonDetailClickListener
{

    class GameViewHolder(val view:GamesCardItemsBinding) : RecyclerView.ViewHolder(view.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<GamesCardItemsBinding>(inflater,
            R.layout.games_card_items,parent,false)
        return GameViewHolder(view)
//        val binding = GamesCardItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.game = games[position]
        holder.view.listener =  this
//        val game = games[position]
//        with(holder.binding) {
//            textViewTitle.text = game.title
//            textViewAuthor.text = game.developer
//            textViewGenre.text = game.genre
//            textViewShortDescription.text = game.description
//            imageViewCard.loadImage(game.imageURL, holder.binding.progressBar)
//
//            buttonDetail.setOnClickListener {
//                Log.d("GameListAdapter", "Navigating with game ID: ${game.gameId}")
//                val action = GameListFragmentDirections.actionGameListFragmentToGameDetailFragment(game.gameId)
//                it.findNavController().navigate(action)
//            }
//
//        }
    }


    override fun getItemCount(): Int {
         return games.size
    }

    fun updateGameList(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = GameListFragmentDirections.actionReadHotel(v.tag as Int)
        Navigation.findNavController(v).navigate(action)
    }
}

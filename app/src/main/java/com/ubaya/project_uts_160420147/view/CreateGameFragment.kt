package com.ubaya.project_uts_160420147.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.project_uts_160420147.databinding.FragmentCreateGameBinding
import com.ubaya.project_uts_160420147.model.Game
import com.ubaya.project_uts_160420147.viewmodel.GameListModel


class CreateGameFragment : Fragment() {
    private lateinit var binding: FragmentCreateGameBinding
//    private lateinit var binding: FragmentCreateGameBinding
    private lateinit var viewModel: GameListModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameListModel::class.java)

        binding.buttonSubmit.setOnClickListener {
            var game = Game(
                binding.editTextTitle.text.toString(),
                binding.editTextDescription.text.toString(),
                binding.editTextDeveloper.text.toString(),
                binding.editTextGenre.text.toString(),
                binding.editTextImageUrl.text.toString()
            )
            val list = listOf(game)
            viewModel.createGame(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}

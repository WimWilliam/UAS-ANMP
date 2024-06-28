package com.ubaya.project_uts_160420147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ubaya.project_uts_160420147.databinding.FragmentCreateNewsBinding
import com.ubaya.project_uts_160420147.model.News
import com.ubaya.project_uts_160420147.viewmodel.DetailViewModel

class CreateNewsFragment : Fragment() {

    private lateinit var binding: FragmentCreateNewsBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: GameDetailFragmentArgs by navArgs()
        val gameId = args.gameId

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding.buttonSubmit.setOnClickListener {
            val news = News(
                title = binding.editTextTitle.text.toString(),
                author = binding.editTextDescription.text.toString(),
                category = binding.editTextDeveloper.text.toString(),
                deskrip = binding.editTextGenre.text.toString(),
                idnewsGame = gameId.toInt()
            )
            viewModel.createNews(listOf(news))
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}

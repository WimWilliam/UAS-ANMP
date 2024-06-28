package com.ubaya.project_uts_160420147.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.databinding.FragmentRegisterBinding
import com.ubaya.project_uts_160420147.model.Akun
import com.ubaya.project_uts_160420147.model.Game
import com.ubaya.project_uts_160420147.model.GameDatabase
import com.ubaya.project_uts_160420147.viewmodel.ProfileViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.buttonRegister.setOnClickListener {
            var akun = Akun(

                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPhoneNumber.text.toString()
            )
            val list = listOf(akun)
            viewModel.register(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}

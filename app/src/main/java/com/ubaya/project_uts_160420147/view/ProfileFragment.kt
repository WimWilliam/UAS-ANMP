package com.ubaya.project_uts_160420147.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ubaya.project_uts_160420147.databinding.FragmentProfilBinding
import com.ubaya.project_uts_160420147.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserData()
        viewModel.akunLD.observe(viewLifecycleOwner) { akun ->
            akun?.let {
                binding.editTextFirstName.setText(it.firstName)
                binding.editTextLastName.setText(it.lastName)
                binding.editTextPassword.setText(it.password)
            }
        }

        binding.buttonSaveChanges.setOnClickListener {
            saveUserData()
        }

        binding.buttonSignOut.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.remove("user_id")
        editor?.apply()

        val action = ProfileFragmentDirections.actionItemProfileToLoginFragment()
        findNavController().navigate(action)
    }

    private fun loadUserData() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getInt("user_id", -1)
        if (userId != -1) {
            Log.d("ProfileFragment", "User ID loaded: $userId")
            viewModel.getAkunById(userId!!)
        } else {
            Log.d("ProfileFragment", "User ID not found")
        }
    }

    private fun saveUserData() {
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        val userId = sharedPreferences?.getInt("user_id", -1) // Mendapatkan ID pengguna dari SharedPreferences

        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val password = binding.editTextPassword.text.toString()

        editor?.apply {
            userId?.let {
                putInt("user_id", it)
            }
            putString("first_name", firstName)
            putString("last_name", lastName)
            putString("password", password)
        }?.apply()

        userId?.let {
            viewModel.updateProfile(it, firstName, lastName, password)
        }
        Toast.makeText(context, "Data saved successfully.", Toast.LENGTH_SHORT).show()
        loadUserData()
    }
}

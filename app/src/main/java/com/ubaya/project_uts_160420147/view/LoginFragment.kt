package com.ubaya.project_uts_160420147.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.databinding.FragmentLoginBinding
import com.ubaya.project_uts_160420147.viewmodel.ProfileViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(context, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
        binding.txtBuatAkun.setOnClickListener {
            navigateToRegisterFragment()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.akunLD.observe(viewLifecycleOwner, Observer { akun ->
            if (akun == null) {
                Toast.makeText(context, "Username/Password Salah", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan ID pengguna ke SharedPreferences saat berhasil login
                saveUserId(akun.id)
                findNavController().navigate(R.id.action_loginFragment_to_itemHome)
                Toast.makeText(context, "Login successful.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveUserId(userId: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("user_id", userId)
        editor.apply()
    }

    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}

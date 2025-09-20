package com.example.uielements.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.uielements.AppViewModel
import com.example.uielements.MainActivity
import com.example.uielements.R
import com.example.uielements.databinding.FragmentButtonsBinding
import com.google.android.material.snackbar.Snackbar

class ButtonsFragment : Fragment() {

    private var _binding: FragmentButtonsBinding? = null
    private val binding get() = _binding!!
    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        binding.titulo.text = "Botones"
        binding.descripcion.text = "Los botones permiten al usuario realizar acciones."

        binding.btnNormal.setOnClickListener {
            Toast.makeText(requireContext(), "Botón normal pulsado", Toast.LENGTH_SHORT).show()
        }

        binding.imgButton.setOnClickListener {
            Snackbar.make(binding.root, "ImageButton accionado", Snackbar.LENGTH_SHORT).show()
        }

        binding.fab.setOnClickListener {
            (activity as? MainActivity)?.let { main ->
                main.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
                    ?.selectedItemId = R.id.nav_list
            }
        }

        appViewModel.userName.observe(viewLifecycleOwner) {
            binding.labelNombreCompartido.text = "Nombre compartido: ${it ?: "(sin definir)"}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.uielements.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.uielements.AppViewModel
import com.example.uielements.databinding.FragmentTextFieldsBinding

class TextFieldsFragment : Fragment() {

    private var _binding: FragmentTextFieldsBinding? = null
    private val binding get() = _binding!!
    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTextFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        binding.titulo.text = "Campos de Texto (EditText)"
        binding.descripcion.text = "Permiten ingresar información del usuario como nombre o correo."

        binding.editNombre.doOnTextChanged { text, _, _, _ ->
            binding.previewNombre.text = "Nombre temporal: ${text ?: ""}"
        }

        binding.editEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                guardar()
                true
            } else false
        }

        binding.btnGuardar.setOnClickListener { guardar() }

        appViewModel.userName.observe(viewLifecycleOwner) { name ->
            if (!name.isNullOrBlank()) {
                binding.labelSalvado.text = "Nombre guardado compartido: $name"
            }
        }
    }

    private fun guardar() {
        val nombre = binding.editNombre.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        if (nombre.isEmpty()) {
            Toast.makeText(requireContext(), "Ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }
        appViewModel.setUserName(nombre)
        appViewModel.setUserEmail(email)
        Toast.makeText(requireContext(), "Guardado: $nombre / $email", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
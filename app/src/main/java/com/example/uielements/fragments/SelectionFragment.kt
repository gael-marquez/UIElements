package com.example.uielements.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.uielements.AppViewModel
import com.example.uielements.databinding.FragmentSelectionBinding

class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        binding.titulo.text = "Elementos de Selección"
        binding.descripcion.text = "Permiten elegir opciones: múltiples, exclusivas y estados."

        binding.checkOpcion.setOnCheckedChangeListener { _, _ -> actualizarResumen() }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val seleccionado = when (binding.radioGroup.checkedRadioButtonId) {
                binding.radioOpcion1.id -> "Opción 1"
                binding.radioOpcion2.id -> "Opción 2"
                else -> "Ninguna"
            }
            appViewModel.setPreferencia(seleccionado)
            actualizarResumen()
        }

        binding.switchModo.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            appViewModel.setModoAvanzado(isChecked)
            actualizarResumen()
        }

        appViewModel.userName.observe(viewLifecycleOwner) { actualizarResumen() }
        appViewModel.preferencia.observe(viewLifecycleOwner) { actualizarResumen() }
        appViewModel.modoAvanzado.observe(viewLifecycleOwner) { actualizarResumen() }
    }

    private fun actualizarResumen() {
        val nombre = appViewModel.userName.value ?: "(sin nombre)"
        val pref = appViewModel.preferencia.value ?: "Ninguna"
        val modo = if (appViewModel.modoAvanzado.value == true) "Avanzado" else "Básico"
        val chk = if (binding.checkOpcion.isChecked) "Check activo" else "Check inactivo"
        binding.resumen.text = "Nombre: $nombre\nPreferencia: $pref\nModo: $modo\n$chk"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
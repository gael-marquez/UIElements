package com.example.uielements.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uielements.AppViewModel
import com.example.uielements.adapter.SimpleItemAdapter
import com.example.uielements.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        binding.titulo.text = "Listas (RecyclerView)"
        binding.descripcion.text = "Muestran colecciones desplazables."

        val items = List(10) { i -> "Elemento ${i + 1}" }
        val adapter = SimpleItemAdapter(items) { item ->
            val nombre = appViewModel.userName.value ?: "Usuario"
            Toast.makeText(requireContext(), "$nombre tocó $item", Toast.LENGTH_SHORT).show()
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
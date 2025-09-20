package com.example.uielements.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.uielements.AppViewModel
import com.example.uielements.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val appViewModel: AppViewModel by activityViewModels()
    private val handler = Handler(Looper.getMainLooper())
    private var progress = 0
    private var running = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        binding.titulo.text = "Elementos de Información"
        binding.descripcion.text = "Muestran datos: texto, imágenes y progreso."

        appViewModel.userName.observe(viewLifecycleOwner) {
            binding.textSaludo.text = "Hola ${it ?: "Visitante"}"
        }

        binding.btnSimularProgreso.setOnClickListener {
            if (!running) iniciarProgreso()
        }
    }

    private fun iniciarProgreso() {
        progress = 0
        running = true
        binding.progressBar.progress = 0
        binding.estadoProgreso.text = "Iniciando..."
        handler.post(object : Runnable {
            override fun run() {
                if (progress <= 100) {
                    binding.progressBar.progress = progress
                    binding.estadoProgreso.text = "Progreso: $progress%"
                    progress += 5
                    handler.postDelayed(this, 150)
                } else {
                    running = false
                    binding.estadoProgreso.text = "Completado"
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        running = false
        _binding = null
    }
}
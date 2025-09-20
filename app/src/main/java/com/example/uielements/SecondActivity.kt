package com.example.uielements

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uielements.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupClickListeners()

        // Recibir datos del Activity anterior (si los hay)
        receiveDataFromPreviousActivity()
    }

    private fun setupViews() {
        // Configurar la toolbar si la tienes
        supportActionBar?.apply {
            title = "Segunda Activity"
            setDisplayHomeAsUpEnabled(true) // Mostrar botón de regreso
        }
    }

    private fun setupClickListeners() {
        // Botón para regresar al MainActivity
        binding.btnBackToMain.setOnClickListener {
            goBackToMainActivity()
        }

        // Botón para finalizar este Activity
        binding.btnFinishActivity.setOnClickListener {
            finish() // Cierra este Activity y regresa al anterior
        }
    }

    private fun receiveDataFromPreviousActivity() {
        // Recibir datos enviados desde el Activity anterior
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
        val correoUsuario = intent.getStringExtra("CORREO_USUARIO")

        // Mostrar los datos recibidos
        binding.tvDataReceived.text = """
            Datos recibidos:
            Nombre: ${nombreUsuario ?: "No especificado"}
            Correo: $correoUsuario
        """.trimIndent()
    }

    private fun goBackToMainActivity() {
        // Simplemente finalizar este Activity (regresa al anterior)
        finish()
    }

    // Manejar el botón de regreso de la ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
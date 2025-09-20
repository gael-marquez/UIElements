package com.example.uielements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.uielements.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import com.example.uielements.fragments.TextFieldsFragment
import com.example.uielements.fragments.ButtonsFragment
import com.example.uielements.fragments.SelectionFragment
import com.example.uielements.fragments.ListFragment
import com.example.uielements.fragments.InfoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val appViewModel: AppViewModel by viewModels()

    // TAGs opcionales para reutilizar instancias
    private companion object {
        const val TAG_TEXT = "frag_text"
        const val TAG_BUTTONS = "frag_buttons"
        const val TAG_SELECTION = "frag_selection"
        const val TAG_LIST = "frag_list"
        const val TAG_INFO = "frag_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            showFragment(TextFieldsFragment(), TAG_TEXT)
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_textfields -> showFragment(TextFieldsFragment(), TAG_TEXT)
                R.id.nav_buttons -> showFragment(ButtonsFragment(), TAG_BUTTONS)
                R.id.nav_selection -> showFragment(SelectionFragment(), TAG_SELECTION)
                R.id.nav_list -> showFragment(ListFragment(), TAG_LIST)
                R.id.nav_info -> showFragment(InfoFragment(), TAG_INFO)
            }
            true
        }

        binding.btnSecondActivity.setOnClickListener {
            val nombre = appViewModel.userName.value ?: ""
            val correo = appViewModel.userEmail.value ?: ""
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NOMBRE_USUARIO", nombre)
            intent.putExtra("CORREO_USUARIO", correo)
            startActivity(intent)
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val fm = supportFragmentManager
        val existing = fm.findFragmentByTag(tag)
        val tx = fm.beginTransaction()
        // Ocultamos todos los ya añadidos
        fm.fragments.forEach { tx.hide(it) }
        if (existing != null) {
            tx.show(existing)
        } else {
            tx.add(R.id.fragmentContainer, fragment, tag)
        }
        tx.commit()
    }
}
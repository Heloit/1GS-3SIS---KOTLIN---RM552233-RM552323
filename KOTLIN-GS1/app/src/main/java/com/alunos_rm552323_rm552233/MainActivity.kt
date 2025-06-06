package com.alunos_rm552323_rm552233

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alunos_rm552323_rm552233.databinding.ActivityMainBinding

import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o ViewModel
        val factory = EventViewModelFactory((application as EventApplication).repository)
        viewModel = ViewModelProvider(this, factory).get(EventViewModel::class.java)

        // Configura o RecyclerView
        adapter = EventAdapter { event -> viewModel.delete(event) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observa os eventos do LiveData
        viewModel.allEvents.observe(this) { events ->
            events?.let { adapter.submitList(it) }
        }

        // Configura o botão Incluir
        binding.btnInclude.setOnClickListener {
            val location = binding.etLocation.text.toString().trim()
            val eventType = binding.etEventType.text.toString().trim()
            val impact = binding.etImpact.text.toString().trim()
            val date = binding.etDate.text.toString().trim()
            val peopleAffected = binding.etPeopleAffected.text.toString().trim()

            // Validação dos campos
            if (location.isEmpty() || eventType.isEmpty() || impact.isEmpty() || date.isEmpty() || peopleAffected.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidDate(date)) {
                Toast.makeText(this, "Data inválida (formato: dd/MM/yyyy)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peopleCount = peopleAffected.toIntOrNull()
            if (peopleCount == null || peopleCount <= 0) {
                Toast.makeText(this, "Número de pessoas afetadas deve ser maior que zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Adiciona o evento
            val event = Event(
                location = location,
                eventType = eventType,
                impact = impact,
                date = date,
                peopleAffected = peopleCount
            )
            viewModel.insert(event)
            clearFields()
            Toast.makeText(this, "Evento adicionado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        // Navega para a tela Sobre
        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }

    private fun clearFields() {
        binding.etLocation.text.clear()
        binding.etEventType.text.clear()
        binding.etImpact.text.clear()
        binding.etDate.text.clear()
        binding.etPeopleAffected.text.clear()
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }
}
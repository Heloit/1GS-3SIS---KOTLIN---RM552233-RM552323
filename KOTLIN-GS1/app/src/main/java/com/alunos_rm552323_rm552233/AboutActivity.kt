package com.alunos_rm552323_rm552233

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alunos_rm552323_rm552233.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvParticipants.text = """
            Participantes:
            - Nome: João Silva, RM: 552323
            - Nome: Maria Oliveira, RM: 552233
        """.trimIndent()
    }
}
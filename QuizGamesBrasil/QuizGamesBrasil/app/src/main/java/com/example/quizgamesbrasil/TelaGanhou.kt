package com.example.quizgamesbrasil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaGanhou : AppCompatActivity() {
    private lateinit var btnRecomecar: ImageButton
    private lateinit var btnHome: ImageButton
    private lateinit var txtPontuacao: TextView
    private lateinit var txtMensagem1: TextView
    private lateinit var txtMensagem2: TextView
    private lateinit var imgResultado: ImageView

    private var pontuacao = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_ganhou)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRecomecar = findViewById(R.id.btnRecomecar)
        btnHome = findViewById(R.id.btnHome)
        txtPontuacao = findViewById(R.id.txtPontuacao)
        txtMensagem1 = findViewById(R.id.txtMensagem1)
        txtMensagem2 = findViewById(R.id.txtMensagem2)
        imgResultado = findViewById(R.id.imgResultado)

        val dados: Bundle? = intent.extras
        pontuacao = dados?.getInt("pontuacao") ?: 0

        txtPontuacao.text = "$pontuacao / 8"

        if(pontuacao < 5)
        {
            txtMensagem1.text = "Que Pena!"
            txtMensagem2.text = "Você Perdeu"
            imgResultado.setImageResource(R.drawable.consolacao)
        }
        else
        {
            txtMensagem1.text = "Parabens!"
            txtMensagem2.text = "Você Ganhou"
            imgResultado.setImageResource(R.drawable.premio)
        }

        btnHome.setOnClickListener {
            finish()
        }

        btnRecomecar.setOnClickListener {
            val intent = Intent(applicationContext, Quiz::class.java)
            startActivity(intent)
            finish()
        }
    }
}
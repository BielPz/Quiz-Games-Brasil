package com.example.quizgamesbrasil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnPlay: ImageButton
    lateinit var frameModos: FrameLayout
    lateinit var txtTitulo: ImageView
    lateinit var btnFechar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnPlay = findViewById(R.id.btnPlay)
        frameModos = findViewById(R.id.frameModos)
        txtTitulo = findViewById(R.id.txtTitulo)
        btnFechar = findViewById(R.id.btnFechar)

        frameModos.visibility = View.GONE
        btnPlay.visibility = View.VISIBLE
        txtTitulo.visibility = View.VISIBLE

        btnFechar.setOnClickListener {
            frameModos.visibility = View.GONE
            btnPlay.visibility = View.VISIBLE
            txtTitulo.visibility = View.VISIBLE
        }
    }

    fun Comecar(view: View)
    {
        frameModos.visibility = View.VISIBLE
        btnPlay.visibility = View.GONE
        txtTitulo.visibility = View.GONE
    }

    fun ModoNormal(view: View)
    {
        MudarTela(0)
    }

    fun ModoDesafio(view: View)
    {
        MudarTela(1)
    }

    fun MudarTela(opcao: Int)
    {
        val intent = Intent(applicationContext, Quiz::class.java)
        intent.putExtra("dificuldade",opcao)
        startActivity(intent)
    }
}
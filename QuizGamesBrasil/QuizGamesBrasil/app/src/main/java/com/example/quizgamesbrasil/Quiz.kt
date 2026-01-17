package com.example.quizgamesbrasil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {

    private  lateinit var radioGroup: RadioGroup
    private lateinit var txtIndicadorPerg: TextView
    private lateinit var txtPergunta: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnVoltar: ImageButton

    private var indicePerguntaAtual = 0
    private var dificuldade = 0
    private var pontuacao = 0

    private val perguntas = listOf(
        Pergunta(
            texto = "Qual a formula quimica da agua?",
            respostaCorreta = 1, // B
            respostas = listOf("A) H3O", "B) H20", "C) NHO3", "D) CO2", "E) NCl2")
        ),
        Pergunta(
            texto = "Qual a cor da água?",
            respostaCorreta = 2, // C
            respostas = listOf("A) Azul", "B) Verde", "C) Incolor", "D) Branca", "E) Transparente")
        ),
        Pergunta(
            texto = "Qual dessas linguagens de programação é compilada?",
            respostaCorreta = 3, // C
            respostas = listOf("C#", "Python", "JavaScript", "C", "Lua")
        ),
        Pergunta(
            texto = "Qual unidade é usada para medir força no Sistema Internacional?",
            respostaCorreta = 4, // E
            respostas = listOf("A) Joule", "B) Watt", "C) Pascal", "D) Forte", "E) Newton")
        ),
        Pergunta(
            texto = "Qual das opções representa um dispositivo de armazenamento não volátil?",
            respostaCorreta = 2, // C
            respostas = listOf("A) RAM", "B) CPU", "C) SSD", "D) GPU", "E) Nenhuma das anteriores")
        ),
        Pergunta(
            texto = "Qual componente eletrônico armazena carga elétrica temporariamente?",
            respostaCorreta = 0, // A
            respostas = listOf("A) Capacitor", "B) Diodo", "C) Transistor", "D) Resistor", "E) Indutor")
        ),
        Pergunta(
            texto = "Qual é o número atômico do oxigênio?",
            respostaCorreta = 3, // D
            respostas = listOf("A) 6", "B) 10", "C) 9", "D) 8", "E) 7")
        ),
        Pergunta(
            texto = "Em um sistema de computação quântica baseado em qubits supercondutores, qual fenômeno físico permite a coerência quântica necessária para a superposição de estados?",
            respostaCorreta = 1, // B
            respostas = listOf(
                "A) Emissão espontânea de fótons",
                "B) Tunelamento quântico",
                "C) Interferência eletromagnética",
                "D) Decaimento beta reverso",
                "E) Efeito Joule"
            )
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtPergunta  = findViewById(R.id.txtPergunta)
        txtIndicadorPerg = findViewById(R.id.txtIndicadorPerg)
        radioGroup = findViewById(R.id.radioGroup)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnVoltar = findViewById(R.id.btnVoltar)

        val dados: Bundle? = intent.extras
        dificuldade = dados?.getInt("dificuldade") ?: 0

        atualizarPergunta()

        radioGroup.setOnCheckedChangeListener{ _, checkedID ->
            if(checkedID != View.NO_ID)
            {
                btnConfirmar.visibility = View.VISIBLE
            }
            else
            {
                btnConfirmar.visibility = View.GONE
            }
        }

        btnVoltar.setOnClickListener{
            finish()
        }
    }

    private fun atualizarPergunta()
    {
        val perguntaAtual = perguntas[indicePerguntaAtual]
        btnConfirmar.visibility = View.INVISIBLE

        txtPergunta.text = perguntaAtual.texto
        txtIndicadorPerg.text = "${indicePerguntaAtual+1}/${perguntas.size}"

        for (i in 0 until perguntaAtual.respostas.size)
        {
            val radioButtonId = resources.getIdentifier("radioButton${i+1}", "id", packageName)
            val radioButton = findViewById<RadioButton>(radioButtonId)
            radioButton.text = perguntaAtual.respostas[i]
        }
    }

    private fun validar(i: Int)
    {
        val mensagem = if (i == perguntas[indicePerguntaAtual].respostaCorreta)
        {
            "Correto!"
        }
        else
        {
            "Incorreto"
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
        if(mensagem == "Incorreto" && dificuldade == 1)
        {
            radioGroup.clearCheck()
            indicePerguntaAtual = 0
            atualizarPergunta()
        }
        else
        {
            if(mensagem == "Correto!" && pontuacao < 8)
            {
                pontuacao += 1
            }

            if((indicePerguntaAtual+1) != perguntas.size)
            {
                indicePerguntaAtual = (indicePerguntaAtual + 1) % perguntas.size
            }
            else
            {
                val intent = Intent(applicationContext, TelaGanhou::class.java)
                intent.putExtra("pontuacao",pontuacao)
                startActivity(intent)
                finish()
            }

            radioGroup.clearCheck()
            atualizarPergunta()
        }
    }

    fun confirmar(view: View)
    {
        val selecionadoId = radioGroup.checkedRadioButtonId

        when (selecionadoId)
        {
            R.id.radioButton1 -> validar(0)
            R.id.radioButton2 -> validar(1)
            R.id.radioButton3 -> validar(2)
            R.id.radioButton4 -> validar(3)
            R.id.radioButton5 -> validar(4)
            else -> Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_LONG).show()
        }
    }
}
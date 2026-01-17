package com.example.quizgamesbrasil

data class Pergunta(
    val texto: String,
    val respostaCorreta: Int,
    val respostas: List<String>
)

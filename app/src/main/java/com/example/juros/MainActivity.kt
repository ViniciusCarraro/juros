package com.example.juros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun simples(view: View) {
        val aa = findViewById<RadioButton>(R.id.aa)
        val edtfinanciamento = findViewById<EditText>(R.id.c)
        val edittaxa = findViewById<EditText>(R.id.i)
        val edittempo = findViewById<EditText>(R.id.t)
        val financiamento = edtfinanciamento?.text.toString().toDouble()
        val taxa = edittaxa?.text.toString().toDouble()
        val tempo = edittempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isSelected) {
            val taxaAA = taxa /12.0
            calcmont = financiamento + (financiamento * taxaAA * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        else {
            calcmont = financiamento + (financiamento * taxa * tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        val intent = Intent(this, MostrarResultadoActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }

    fun composto(view: View) {
        val aa = findViewById<RadioButton>(R.id.aa)
        val edtfinanciamento = findViewById<EditText>(R.id.c)
        val edittaxa = findViewById<EditText>(R.id.i)
        val edittempo = findViewById<EditText>(R.id.t)
        val financiamento = edtfinanciamento?.text.toString().toDouble()
        val taxa = edittaxa?.text.toString().toDouble()
        val tempo = edittempo?.text.toString().toDouble()
        var calcmont = 0.0
        var calcvalor = 0.0
        var calcparcela = 0.0
        if (aa.isSelected) {
            val taxaAA = Math.pow(1+taxa,(1/12.0))-1
            calcmont = financiamento * (1.0+taxaAA).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        else {
            calcmont = financiamento * (1.0+taxa).pow(tempo)
            calcvalor = calcmont - financiamento
            calcparcela = calcmont / tempo
        }
        val intent = Intent(this, MostrarResultadoActivity::class.java).apply {
            putExtra("calcmont", calcmont)
            putExtra("calcvalor", calcvalor)
            putExtra("calcparcela", calcparcela)
        }
        startActivity(intent)
    }
}
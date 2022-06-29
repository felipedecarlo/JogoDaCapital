package com.example.capitaisgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    List<Estado> listaEstado = new ArrayList<>();
    List<Integer> listaSorteio = new ArrayList<>();
    int rodada = 0, pontos = 0;
    String estadoAtual, capitalAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEstado.add(new Estado("Acre", "Rio Branco"));
        listaEstado.add(new Estado("Amazonas", "Manaus"));
        listaEstado.add(new Estado("Bahia", "Salvador"));
        listaEstado.add(new Estado("Ceará", "Fortaleza"));
        listaEstado.add(new Estado("Mato Grosso do Sul", "Campo Grande"));
        listaEstado.add(new Estado("Minas Gerais", "Belo Horizonte"));
        listaEstado.add(new Estado("Paraná", "Curitiba"));
        listaEstado.add(new Estado("Pernambuco", "Recife"));
        listaEstado.add(new Estado("Piauí", "Teresina"));
        listaEstado.add(new Estado("Rio de Janeiro", "Rio de Janeiro"));
        listaEstado.add(new Estado("Rio Grande do Norte", "Natal"));
        listaEstado.add(new Estado("Rio Grande do Sul", "Porto Alegre"));
        listaEstado.add(new Estado("Rondônia", "Porto Velho"));
        listaEstado.add(new Estado("Roraima", "Boa Vista"));
        listaEstado.add(new Estado("Tocantins", "Palmas"));

        sorteio();
        setaRodada();
    }

    public void sorteio() {
        Random r = new Random();

        pontos = 0;
        listaSorteio.clear();

        for (int i = 0; i < 5; i++)
            listaSorteio.add(r.nextInt(15));
    }

    public void setaRodada() {

        EditText input          = findViewById(R.id.editTextInputCapital);
        TextView outputEstado   = findViewById(R.id.textViewOutputEstado);
        TextView outputResposta = findViewById(R.id.textViewOutputResposta);
        Button btnResponder     = findViewById(R.id.buttonResponder);
        Button btnNext          = findViewById(R.id.buttonNext);

        estadoAtual = listaEstado.get(listaSorteio.get(rodada)).nome;
        capitalAtual = listaEstado.get(listaSorteio.get(rodada)).capital;

        outputEstado.setText(estadoAtual);
        input.setText("");
        outputResposta.setText("");


        btnResponder.setEnabled(true);
        btnNext.setEnabled(false);
    }

    public void responder(View view) {

        EditText input          = findViewById(R.id.editTextInputCapital);
        TextView outputResposta = findViewById(R.id.textViewOutputResposta);
        TextView outputPontos   = findViewById(R.id.textViewOutputPontos);
        Button btnResponder     = findViewById(R.id.buttonResponder);
        Button btnNext          = findViewById(R.id.buttonNext);

        if (input.length() == 0)
            Toast.makeText(this, "Informe a Capital!!!", Toast.LENGTH_SHORT).show();
        else {
            if (input.getText().toString().trim().equalsIgnoreCase(capitalAtual)) {
                outputResposta.setText("ACERTOU!!!");
                pontos += 10;
            } else {
                outputResposta.setText("ERROU!!!");
            }

            outputPontos.setText(pontos + " pontos");
            btnResponder.setEnabled(false);

            if (rodada < 4)
                btnNext.setEnabled(true);
        }
    }

    public void proximo(View view) {
        rodada++;

        setaRodada();
    }

}
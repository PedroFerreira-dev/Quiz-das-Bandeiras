package br.edu.quizbandeiras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class canada extends AppCompatActivity {

    // Seus componentes de tela
    RadioGroup rdgOpcoes9;
    Button btnResponder9;

    // Variáveis para guardar os dados recebidos da tela anterior
    String nomeJogador;
    int pontuacaoAtual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canada);

        // Conecta as variáveis Java com os componentes do XML
        rdgOpcoes9 = findViewById(R.id.rdgOpcoes9);
        btnResponder9 = findViewById(R.id.btnResponder9);

        btnResponder9.setEnabled(false);


        rdgOpcoes9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder9.setEnabled(true);
            }
        });

        // Recebe os dados da tela anterior (Japão)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // Define a ação do botão
        btnResponder9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes9.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(canada.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (idSelecionado == R.id.rdbCorrect9) { // ID da resposta correta
                        pontuacaoAtual++; // Incrementa a pontuação correta
                        Toast.makeText(canada.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(canada.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // Passa os dados para a última tela de pergunta (Portugal)
                    Intent proximoIntent = new Intent(canada.this, portugal.class);
                    proximoIntent.putExtra("NOME_JOGADOR", nomeJogador);
                    proximoIntent.putExtra("PONTUACAO", pontuacaoAtual);
                    startActivity(proximoIntent);
                    finish();
                }
            }
        });
        // Intercepta o botão voltar
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Vai direto para a tela inicial caso o botão "Voltar" seja pressionado
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                finish(); // Fecha a activity atual
            }
        });
    }
}
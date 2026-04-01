package br.edu.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class china extends AppCompatActivity {

    RadioGroup rdgOpcoes1;
    Button btnResponder1;

    // Variáveis para guardar os dados recebidos
    String nomeJogador;
    int pontuacaoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china);

        rdgOpcoes1 = findViewById(R.id.rdgOpcoes1); // Verifique se o ID está correto no seu XML
        btnResponder1 = findViewById(R.id.btnResponder1);
        btnResponder1.setEnabled(false);


        rdgOpcoes1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder1.setEnabled(true);
            }
        });

        // ==========================================================
        //  PASSO 1: RECEBER OS DADOS DA TELA ANTERIOR
        // ==========================================================
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // ==========================================================

        btnResponder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes1.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(china.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (idSelecionado == R.id.rdbCorrect1) { // ID da resposta correta
                        pontuacaoAtual++; // Aumenta a pontuação se acertou
                        Toast.makeText(china.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(china.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // ==========================================================
                    //  PASSO 2: PASSAR OS DADOS ATUALIZADOS PARA A PRÓXIMA TELA
                    // ==========================================================
                    Intent proximoIntent = new Intent(china.this, jamaica.class);
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

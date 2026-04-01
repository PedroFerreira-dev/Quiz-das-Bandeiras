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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class jamaica extends AppCompatActivity {

    // Seus componentes de tela
    RadioGroup rdgOpcoes2;
    Button btnResponder2;

    // vvvvvvv CORREÇÃO 1 vvvvvvv
    // Variáveis para guardar os dados recebidos da tela anterior
    String nomeJogador;
    int pontuacaoAtual;
    // ^^^^^^^ CORREÇÃO 1 ^^^^^^^

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jamaica);

        // Conectar as variáveis Java com os componentes do XML
        rdgOpcoes2 = findViewById(R.id.rdgOpcoes2);
        btnResponder2 = findViewById(R.id.btnResponder2);

        btnResponder2.setEnabled(false);


        rdgOpcoes2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder2.setEnabled(true);
            }
        });

        // Receber os dados da tela anterior (china.java)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);


        // Definir a ação que o botão vai fazer ao ser clicado
        btnResponder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes2.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(jamaica.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (idSelecionado == R.id.rdbCorrect2) {
                        // vvvvvvv CORREÇÃO 2 vvvvvvv
                        pontuacaoAtual++; // Aumentando a pontuação correta
                        // ^^^^^^^ CORREÇÃO 2 ^^^^^^^
                        Toast.makeText(jamaica.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(jamaica.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // Passar os dados atualizados para a próxima tela (italia.java)
                    Intent proximoIntent = new Intent(jamaica.this, italia.class);
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

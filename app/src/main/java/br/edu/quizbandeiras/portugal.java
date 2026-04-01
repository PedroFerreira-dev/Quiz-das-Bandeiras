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

public class portugal extends AppCompatActivity {

    // Atributos com número 10
    RadioGroup rdgOpcoes10;
    Button btnResponder10;

    // Variáveis para guardar os dados
    String nomeJogador;
    int pontuacaoAtual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portugal);

        // Conecta com os componentes do XML
        rdgOpcoes10 = findViewById(R.id.rdgOpcoes10);
        btnResponder10 = findViewById(R.id.btnResponder10);

        btnResponder10.setEnabled(false);


        rdgOpcoes10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder10.setEnabled(true);
            }
        });

        // Recebe os dados da tela anterior (Canadá)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // Define a ação do botão
        btnResponder10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes10.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(portugal.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (idSelecionado == R.id.rdbCorrect10) { // ID da resposta correta
                        pontuacaoAtual++;
                        Toast.makeText(portugal.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(portugal.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // ############################################################
                    // ## ATENÇÃO: Passa os dados para a TELA DE FINALIZAÇÃO     ##
                    // ############################################################
                    Intent proximoIntent = new Intent(portugal.this, finalizacao.class);
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
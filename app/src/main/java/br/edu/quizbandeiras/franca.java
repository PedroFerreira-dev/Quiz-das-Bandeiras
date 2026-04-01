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

public class franca extends AppCompatActivity {

    // Atributos com número 4
    RadioGroup rdgOpcoes4;
    Button btnResponder4;

    // Variáveis para guardar os dados que vêm da tela anterior (Itália)
    String nomeJogador;
    int pontuacaoAtual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franca);

        // Conecta as variáveis Java com os componentes do XML
        rdgOpcoes4 = findViewById(R.id.rdgOpcoes4);
        btnResponder4 = findViewById(R.id.btnResponder4);

        btnResponder4.setEnabled(false);


        rdgOpcoes4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder4.setEnabled(true);
            }
        });

        // Recebe os dados da tela anterior (italia.java)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // Define a ação do botão
        btnResponder4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes4.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(franca.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    // Verifica se a resposta está correta
                    if (idSelecionado == R.id.rdbCorrect4) { // Lembre-se de ter esse ID no seu XML
                        pontuacaoAtual++;
                        Toast.makeText(franca.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(franca.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // Passa os dados atualizados para a próxima tela (ex: uruguai.class)
                    Intent proximoIntent = new Intent(franca.this, uruguai.class); // MUDE AQUI para a próxima tela
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
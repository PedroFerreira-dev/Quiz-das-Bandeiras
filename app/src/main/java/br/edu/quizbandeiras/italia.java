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

public class italia extends AppCompatActivity {

    // Atributos com número 3
    RadioGroup rdgOpcoes3;
    Button btnResponder3;

    // Variáveis para guardar os dados que vêm da tela anterior (Jamaica)
    String nomeJogador;
    int pontuacaoAtual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML para esta activity
        setContentView(R.layout.activity_italia);

        // Conecta as variáveis Java com os componentes do XML
        rdgOpcoes3 = findViewById(R.id.rdgOpcoes3);
        btnResponder3 = findViewById(R.id.btnResponder3);

        btnResponder3.setEnabled(false);


        rdgOpcoes3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder3.setEnabled(true);
            }
        });

        // Recebe os dados da tela anterior (jamaica.java)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // Define a ação do botão
        btnResponder3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes3.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(italia.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    // Verifica se a resposta está correta
                    if (idSelecionado == R.id.rdbCorrect3) { // <<< Lembre-se de ter esse ID no seu XML
                        pontuacaoAtual++;
                        Toast.makeText(italia.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(italia.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // Passa os dados atualizados para a próxima tela (ex: franca.class)
                    Intent proximoIntent = new Intent(italia.this, franca.class); // <<< MUDE AQUI para a próxima tela
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
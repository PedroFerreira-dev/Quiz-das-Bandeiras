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

public class colombia extends AppCompatActivity {

    // Atributos com número 6
    RadioGroup rdgOpcoes6;
    Button btnResponder6;

    // Variáveis para guardar os dados
    String nomeJogador;
    int pontuacaoAtual;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colombia);

        // Conecta com os componentes do XML
        rdgOpcoes6 = findViewById(R.id.rdgOpcoes6);
        btnResponder6 = findViewById(R.id.btnResponder6);

        btnResponder6.setEnabled(false);


        rdgOpcoes6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se uma opção foi marcada, habilita o botão
                btnResponder6.setEnabled(true);
            }
        });

        // Recebe os dados da tela anterior (Uruguai)
        Intent intentAnterior = getIntent();
        nomeJogador = intentAnterior.getStringExtra("NOME_JOGADOR");
        pontuacaoAtual = intentAnterior.getIntExtra("PONTUACAO", 0);

        // Define a ação do botão
        btnResponder6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSelecionado = rdgOpcoes6.getCheckedRadioButtonId();

                if (idSelecionado == -1) {
                    Toast.makeText(colombia.this, "Por favor, escolha uma resposta.", Toast.LENGTH_SHORT).show();
                } else {
                    if (idSelecionado == R.id.rdbCorrect6) { // ID da resposta correta
                        pontuacaoAtual++;
                        Toast.makeText(colombia.this, "Certo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(colombia.this, "Errado!", Toast.LENGTH_SHORT).show();
                    }

                    // Passa os dados para a próxima tela (Coreia)
                    Intent proximoIntent = new Intent(colombia.this, coreia.class);
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

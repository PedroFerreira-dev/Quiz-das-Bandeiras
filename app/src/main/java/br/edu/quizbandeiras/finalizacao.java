package br.edu.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class finalizacao extends AppCompatActivity {

    TextView txtNomeJogador, txtPontuacao;
    Button btnJogarNovamente, btnTelaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Conecta este código com o seu layout XML
        setContentView(R.layout.activity_finalizacao);

        // Conecta os componentes da tela com as variáveis Java
        txtNomeJogador = findViewById(R.id.txtNomeJogador);
        txtPontuacao = findViewById(R.id.txtPontuacao);
        btnJogarNovamente = findViewById(R.id.btnJogarNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);

        // 1. RECEBE OS DADOS FINAIS (nome e pontuação)
        Intent intent = getIntent();
        String nome = intent.getStringExtra("NOME_JOGADOR");
        int pontuacaoFinal = intent.getIntExtra("PONTUACAO", 0);

        // 2. MOSTRA OS DADOS NA TELA
        txtNomeJogador.setText("Jogador: " + nome);
        txtPontuacao.setText(String.valueOf(pontuacaoFinal)); // Converte o número para texto

        // ------------------------------------------------------------------
        // 3. LÓGICA DOS BOTÕES
        // ------------------------------------------------------------------

        // 🕹️ Botão "Jogar novamente"
        btnJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para voltar à primeira pergunta (china.class)
                Intent intentJogarNovamente = new Intent(finalizacao.this, china.class);

                // Envia o nome do jogador novamente para o novo jogo
                intentJogarNovamente.putExtra("NOME_JOGADOR", nome);
                intentJogarNovamente.putExtra("PONTUACAO", 0); // Reseta a pontuação para 0

                startActivity(intentJogarNovamente);
                finish(); // Fecha a tela de finalização
            }
        });

        // 🏠 Botão "Tela principal"
        btnTelaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para voltar para a MainActivity
                Intent intentTelaPrincipal = new Intent(finalizacao.this, MainActivity.class);

                // Limpa o histórico de telas para que o usuário não volte para o quiz ao pressionar "voltar"
                intentTelaPrincipal.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intentTelaPrincipal);
                finish(); // Fecha a tela de finalização
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
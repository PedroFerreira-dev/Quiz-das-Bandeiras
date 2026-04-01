package br.edu.quizbandeiras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private Button btnComecar;
    private Button btnSair;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontre as Views no layout usando seus IDs
        edtNome = findViewById(R.id.edtNome);
        btnComecar = findViewById(R.id.btnComecar);
        btnSair = findViewById(R.id.btnSair);

        // --- Lógica para habilitar/desabilitar o botão ---
        btnComecar.setEnabled(false); // Botão começa desabilitado
        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                // Habilita o botão apenas se o campo de texto não estiver vazio
                btnComecar.setEnabled(!s.toString().trim().isEmpty());
            }
        });

        // ======================================================================
        // 👉 LÓGICA PARA ABRIR A PRÓXIMA TELA (O QUE VOCÊ PEDIU)
        // ======================================================================
        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o nome que o jogador digitou
                String nomeJogador = edtNome.getText().toString();

                // Cria a intenção para ir para a primeira pergunta (china.class)
                Intent intent = new Intent(MainActivity.this, china.class);

                // 🏆 ANEXA OS DADOS PARA A PRÓXIMA TELA 🏆
                intent.putExtra("NOME_JOGADOR", nomeJogador);
                intent.putExtra("PONTUACAO", 0); // Pontuação começa em 0

                // Inicia a próxima tela, levando os dados junto
                startActivity(intent);
            }
        });

        // --- Lógica do botão Sair ---
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha o aplicativo
            }
        });

        // CORREÇÃO: O ID aqui deve ser o do layout principal da MainActivity,
        // geralmente é "main" ou "root". Verifique no seu XML activity_main.xml
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
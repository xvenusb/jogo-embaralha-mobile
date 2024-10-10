package com.example.mariaclaraferreiramendes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.GridLayout;
import android.content.res.ColorStateList;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvSobrenome;
    private TextView tvMatricula;
    private TextView tvMensagem;
    private Button buttonEmbaralhar;
    private Button buttonReset;

    // Armazenar os textos originais
    private String nomeOriginal = "MARIA CLARA";
    private String sobrenomeOriginal = "FERREIRA MENDES";
    private String matriculaOriginal = "200028802";

    // Armazenar as versões ocultas
    private String nomeOculto;
    private String sobrenomeOculto;
    private String matriculaOculta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciando os elementos do layout no código Java
        tvNome = findViewById(R.id.tv_nome);
        tvSobrenome = findViewById(R.id.tv_sobrenome);
        tvMatricula = findViewById(R.id.tv_matricula);
        tvMensagem = findViewById(R.id.tv_mensagem);
        buttonEmbaralhar = findViewById(R.id.button);
        buttonReset = findViewById(R.id.button2);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Inicializando os textos com as versões ocultas
        esconderTexto();

        // Inicializando os botões com letras e números
        criarBotoes(gridLayout);

        // Definindo ações para os botões
        buttonEmbaralhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarBotoes(gridLayout); // Recria os botões embaralhados
                tvMensagem.setText("Teclado já embaralhado!");
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTexto();
            }
        });
    }

    private void esconderTexto() {
        // Ocultar algumas letras com asteriscos
        nomeOculto = "**ri* Cl*r*"; // Exemplo: ocultar algumas letras do nome
        sobrenomeOculto = "F*rr*ir* *nd*s"; // Exemplo: ocultar algumas letras do sobrenome
        matriculaOculta = "20******"; // Exemplo: ocultar parte da matrícula

        tvNome.setText(nomeOculto);
        tvSobrenome.setText(sobrenomeOculto);
        tvMatricula.setText(matriculaOculta);
    }

    private void criarBotoes(GridLayout gridLayout) {
        gridLayout.removeAllViews(); // Limpa os botões existentes

        // Montando as letras e números do teclado, incluindo espaços vazios
        String letrasNumeros = "A E M 200028802 "; // Incluindo espaço no final
        List<String> botoes = new ArrayList<>();

        for (char c : letrasNumeros.toCharArray()) {
            botoes.add(String.valueOf(c));
        }

        // Embaralhar os botões
        Collections.shuffle(botoes);

        // Criar os botões no GridLayout
        for (String letra : botoes) {
            Button button = new Button(this);
            button.setText(letra);
            button.setTextSize(20);
            button.setTextColor(Color.parseColor("#827717"));
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

            // Adiciona lógica para o clique
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!letra.equals(" ")) { // Ignora espaços
                        revelarLetra(letra.charAt(0));
                    }
                }
            });

            // Adiciona o botão ao GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(5, 5, 5, 5);
            gridLayout.addView(button, params);
        }
    }

    private void revelarLetra(char letra) {
        // Lógica para revelar letras
        if (nomeOculto.contains("*")) {
            nomeOculto = nomeOculto.replaceFirst("\\*", String.valueOf(letra));
            tvNome.setText(nomeOculto);
        } else if (sobrenomeOculto.contains("*")) {
            sobrenomeOculto = sobrenomeOculto.replaceFirst("\\*", String.valueOf(letra));
            tvSobrenome.setText(sobrenomeOculto);
        } else if (matriculaOculta.contains("*")) {
            matriculaOculta = matriculaOculta.replaceFirst("\\*", String.valueOf(letra));
            tvMatricula.setText(matriculaOculta);
        }
    }

    // Função para resetar o texto
    private void resetTexto() {
        nomeOculto = "M*RI*A"; // Exemplo: ocultar novamente
        sobrenomeOculto = "F***** G***** L***"; // Ocultar novamente
        matriculaOculta = "20******"; // Ocultar novamente

        tvNome.setText(nomeOculto);
        tvSobrenome.setText(sobrenomeOculto);
        tvMatricula.setText(matriculaOculta);
        tvMensagem.setText("Texto Resetado");
    }
}
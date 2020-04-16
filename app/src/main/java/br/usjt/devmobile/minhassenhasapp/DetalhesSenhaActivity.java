package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalhesSenhaActivity extends AppCompatActivity {

    private Senha senha;
    private TextView textViewSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        senha = (Senha)intent.getSerializableExtra("senha");
        textViewSenha = findViewById(R.id.textViewNomeValue);
        textViewSenha.setText(senha.getNome());
    }
    /*public View getView(final int position, View convertView, ViewGroup parent) {
        final View viewLista = layoutInflater.inflate(R.layout.activity_detalhe_senha, null);
        TextView textViewSenha = (TextView) viewLista.findViewById(R.id.textViewNomeValue);
        }*/
    }

package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private RoundedImageView imagemMain;
    private LinearLayout layoutImagem;

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista_senhas, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        String nomeSelecionado = (String)
                getListView().getItemAtPosition(info.position);

        switch (item.getItemId()) {
            case R.id.opcao1:
                Toast.makeText(this, "Opção 1 - "+
                        nomeSelecionado, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcao2:
                Toast.makeText(this, "Opção 2 - "+
                        nomeSelecionado, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcao3:
                Toast.makeText(this, "Opção 3 - "+
                        nomeSelecionado, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private AdapterView getListView() {
        return getListView();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        usuario = findViewById(R.id.emailEditTextInput);
        senha = findViewById(R.id.passwordEditTextInput);
        imagemMain = findViewById(R.id.userImageMain);
        layoutImagem = findViewById(R.id.layoutImagemMain);

        colocaImagem();

    }

    private void colocaImagem(){
        if(Hawk.contains("imagem")){
            String path = Hawk.get("imagem");
            imagemMain.setImageURI(Uri.fromFile(new File(path)));
            layoutImagem.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        colocaImagem();
    }
    public static boolean senhaForte(String senha) {
        if (senha.length() < 8) return false;

        boolean numero = false;
        boolean maiuscula = false;
        boolean minuscula = false;
        boolean simbolo = false;
        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numero = true;
            } else if (c >= 'A' && c <= 'Z') {
                maiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                minuscula = true;
            } else {
                simbolo = true;
            }
        }
        return numero && maiuscula && minuscula && simbolo;
    }

    public void fazerLogin(View view){

        if(usuario.getText().toString().equals(Hawk.get("usuario")) &&
            senha.getText().toString().equals(Hawk.get("senha"))){

            Intent intent = new Intent(this, ListasSenhasActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Usuário ou senha incorretos!",Toast.LENGTH_SHORT).show();
        }
        if(usuario.getText().length() == 0 || senha.getText().length() == 0){
            Toast.makeText(getApplication(),"Os campo Usuario e Senha são Obrigatorios", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplication(),"Seja Bem Vindo" + usuario.getText().toString(), Toast.LENGTH_SHORT).show();
        }



    }

    public void novoCadastro(View view){

        if(Hawk.contains("usuario")){
            Toast.makeText(this,"Usuário já cadastrado!",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, CadastroUsuarioActivity.class);
            startActivity(intent);
        }


    }
}

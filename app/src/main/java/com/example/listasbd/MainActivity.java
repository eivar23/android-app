package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void anime(View view){
       int id_usuario = getIntent().getIntExtra("dato2",0);
       Intent pag_anime = new Intent(this, MainActivity2.class);
       pag_anime.putExtra("dato", id_usuario);
       startActivity(pag_anime);
    }
    public void musica(View view){
        int id_usuario = getIntent().getIntExtra("dato2",0);
        Intent pag_musica = new Intent(this, MainActivity3.class);
        pag_musica.putExtra("dato", id_usuario);
        startActivity(pag_musica);
    }
    public void libros(View view){
        int id_usuario = getIntent().getIntExtra("dato2",0);
        Intent pag_libros = new Intent(this, MainActivity4.class);
        pag_libros.putExtra("dato", id_usuario);
        startActivity(pag_libros);
    }
}
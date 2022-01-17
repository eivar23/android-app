package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class verCanciones extends AppCompatActivity {
    TextView txtcancion, txtgenero, txtdificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_canciones);
        txtcancion = (TextView) findViewById(R.id.txt_cancion1);
        txtgenero = (TextView) findViewById(R.id.txt_genero1);
        txtdificultad = (TextView) findViewById(R.id.txt_dificultad1);

        String nombre = getIntent().getStringExtra("dato");
        txtcancion.setText(nombre);
        String genero = getIntent().getStringExtra("dato2");
        txtgenero.setText(genero);
        String dificult = getIntent().getStringExtra("dato3");
        txtdificultad.setText(dificult);
    }
}
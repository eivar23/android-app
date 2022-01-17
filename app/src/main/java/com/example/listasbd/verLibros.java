package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class verLibros extends AppCompatActivity {

    TextView nLibro, gLibro, iLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);
        nLibro = (TextView) findViewById(R.id.txt_nLibro);
        gLibro = (TextView) findViewById(R.id.txt_gLibro);
        iLibro = (TextView) findViewById(R.id.txt_iLibro);

        String nombre = getIntent().getStringExtra("dato");
        nLibro.setText(nombre);
        String genero = getIntent().getStringExtra("dato2");
        gLibro.setText(genero);
        String idioma = getIntent().getStringExtra("dato3");
        iLibro.setText(idioma);
        iLibro.setText(idioma);
    }
}
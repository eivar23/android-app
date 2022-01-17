package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class verAnime extends AppCompatActivity {

        TextView txtAnimedes, txtAnimeti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_anime);
        txtAnimedes = (TextView) findViewById(R.id.txt_animedes);
        txtAnimeti = (TextView) findViewById(R.id.txt_animetipo);

        String descripcion = getIntent().getStringExtra("dato");
        txtAnimedes.setText(descripcion);
        String tipo = getIntent().getStringExtra("dato2");
        txtAnimeti.setText(tipo);


    }
}
package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ingresarAnime extends AppCompatActivity {

    EditText etNombre;
    Spinner spTipoA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_anime);
        etNombre = (EditText) findViewById(R.id.et_nombre);
        spTipoA = (Spinner) findViewById(R.id.sp_tipoA);

        String[] opcionesA ={"acción", "aventura", "comedia", "ciencia ficción", "romance", "terror", "misterio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesA);
        spTipoA.setAdapter(adapter);

    }

    public void ingresarAnime(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String descripcion = etNombre.getText().toString();
        String tipo = spTipoA.getSelectedItem().toString();

        if (descripcion.isEmpty()||tipo.isEmpty()){
            Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues registro = new ContentValues();
            registro.put("descripcion", descripcion);
            registro.put("tipo", tipo);
            registro.put("us_anime", id_usuario);

            BaseDatos.insert("anime", null, registro);
            BaseDatos.close();
            etNombre.setText("");
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
        }
    }





}
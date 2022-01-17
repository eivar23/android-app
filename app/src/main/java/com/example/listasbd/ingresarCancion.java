package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ingresarCancion extends AppCompatActivity {
    EditText et_nombreC;
    Spinner sp_genero, sp_difucultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_cancion);
        et_nombreC = (EditText) findViewById(R.id.et_cancion);
        sp_genero = (Spinner) findViewById(R.id.sp_genero);
        sp_difucultad = (Spinner) findViewById(R.id.sp_dificultad);

        String[] opGenero ={"rock", "balada", "independiente", "metal", "reggae", "clasica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opGenero);
        sp_genero.setAdapter(adapter);

        String[] opDificultad ={"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opDificultad);
        sp_difucultad.setAdapter(adapter1);


    }

    public void ingresarCancion(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String nombre = et_nombreC.getText().toString();
        String genero = sp_genero.getSelectedItem().toString();
        int dificultad = sp_difucultad.getSelectedItemPosition();

      if (nombre.isEmpty()){
            Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("genero", genero);
            registro.put("dificultad", dificultad);
            registro.put("us_cancion", id_usuario);

            BaseDatos.insert("canciones", null, registro);
            BaseDatos.close();
            et_nombreC.setText("");
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
        }

    }
}
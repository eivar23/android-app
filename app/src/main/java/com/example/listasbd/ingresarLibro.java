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

public class ingresarLibro extends AppCompatActivity {
    EditText etNombreL;
    Spinner sp_genero, sp_idioma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_libro);

        etNombreL = (EditText) findViewById(R.id.et_libro);
        sp_genero = (Spinner) findViewById(R.id.sp_generoL);
        sp_idioma = (Spinner) findViewById(R.id.sp_idiomaL);

        String[] opGenero ={"científico", "literatura", "biografía"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opGenero);
        sp_genero.setAdapter(adapter);

        String[] opIdioma ={"español", "inglés", "francés"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opIdioma);
        sp_idioma.setAdapter(adapter2);

    }

    public void ingresarLibro(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String nombre = etNombreL.getText().toString();
        String genero = sp_genero.getSelectedItem().toString();
        String idioma = sp_idioma.getSelectedItem().toString();

        if (nombre.isEmpty()){
            Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("genero", genero);
            registro.put("idioma", idioma);
            registro.put("us_libro", id_usuario);

            BaseDatos.insert("libros", null, registro);
            BaseDatos.close();
            etNombreL.setText("");
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
        }
    }
}
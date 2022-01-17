package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText et_usuario;
    EditText et_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_usuario = (EditText) findViewById(R.id.et_usuario_log);
        et_pass = (EditText) findViewById(R.id.et_pass_log);

    }

    public void Registro(View view) {
        Intent pag_registro = new Intent(this, Registrarse.class);
        startActivity(pag_registro);
    }

    public void ingresar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String usuario = et_usuario.getText().toString();
        String pass = et_pass.getText().toString();
        int id_usuario = 0;
        int cont = 0;

        if (usuario.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM usuario", null);

            int i = 0;
            Cursor fil = BaseDatos.rawQuery("select * from usuario", null);
            while (i < numRows) {
                if (fil.moveToPosition(i)) {
                    if (fil.getString(1).equals(usuario) && fil.getString(3).equals(pass)) {
                        id_usuario = Integer.parseInt(fil.getString(0));
                        cont = 1;
                        Intent pag_inicio = new Intent(this, MainActivity.class);
                        pag_inicio.putExtra("dato2", id_usuario);
                        startActivity(pag_inicio);
                        BaseDatos.close();
                    }
                }
                i++;
            }
        }
        if (cont == 0){
            Toast.makeText(this, "pelagato no encontrado", Toast.LENGTH_SHORT).show();

        }

    }
}
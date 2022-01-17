package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {

    EditText et_usuario1;
    EditText et_correo;
    EditText et_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        et_usuario1 = (EditText) findViewById(R.id.et_reg_usuario);
        et_correo = (EditText) findViewById(R.id.et_reg_correo);
        et_contraseña = (EditText) findViewById(R.id.pass_reg_contra);
    }

    public void registraUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String usuario = et_usuario1.getText().toString();
        String correo = et_correo.getText().toString();
        String pass = et_contraseña.getText().toString();

        if (!usuario.isEmpty()&&!pass.isEmpty()){
            int cont = 0, cont1 = 0;
            for (int i = 0; i <usuario.length(); i++){
                cont++;
            }
            for (int i = 0; i <pass.length(); i++){
                cont1++;
            }

            if(cont < 4 || cont1 < 4) {
                Toast.makeText(this, "Usuario y contraseña deben contener 4 caracteres", Toast.LENGTH_SHORT).show();
            }else{

                ContentValues registro = new ContentValues();
                registro.put("user", usuario);
                registro.put("correo", correo);
                registro.put("pass", pass);

                BaseDatos.insert("usuario", null, registro);
                BaseDatos.close();
                et_usuario1.setText("");
                et_correo.setText("");
                et_contraseña.setText("");
                Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
            }



        } else{
            Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void iniciarSesion(View view){
        Intent pag_sesion = new Intent(this, Login.class);
        startActivity(pag_sesion);
    }
}
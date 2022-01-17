package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void ingresarCancion(View view){
       int id_usuario = getIntent().getIntExtra("dato",0);
       Intent pag_cancion = new Intent(this, ingresarCancion.class);
       pag_cancion.putExtra("dato",id_usuario);
       startActivity(pag_cancion);
    }

    public void verListadoCanciones(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        String consulta_nom = "";
        String consulta_gen = "";
        String consulta_dif = "";

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM canciones", null);


        int i = 0;
        Cursor fila = BaseDatos.rawQuery("select * from canciones where us_cancion="+id_usuario,null);
        while (i < numRows){
            if(fila.moveToPosition(i)){
                //id_usuario = Integer.parseInt(fila.getString(0));
                consulta_nom += fila.getString(1)+"  "+"\n";
                consulta_gen += fila.getString(2)+"  "+"\n";
                consulta_dif += fila.getString(3)+"  "+"\n";
                BaseDatos.close();
                // Toast.makeText(this, "id usuario:  " + id_usuario, Toast.LENGTH_SHORT).show();
            }
            i++;
        }


        Intent pag_verMusica = new Intent(this, verCanciones.class);
        pag_verMusica.putExtra("dato", consulta_nom);
        pag_verMusica.putExtra("dato2", consulta_gen);
        pag_verMusica.putExtra("dato3", consulta_dif);
        startActivity(pag_verMusica);

    }
    public void eliminarCancion(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        Intent eli_cancion = new Intent(this, eliminarCancion.class);
        eli_cancion.putExtra("dato",id_usuario);
        startActivity(eli_cancion);
    }
}
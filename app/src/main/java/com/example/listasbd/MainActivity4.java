package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
    public void ingresarLibro(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        Intent pag_libro = new Intent(this, ingresarLibro.class);
        pag_libro.putExtra("dato",id_usuario);
        startActivity(pag_libro);
    }
    public void verListadoLibros(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        String consulta_nLibro = "";
        String consulta_gLibro = "";
        String consulta_iLibro = "";

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM libros", null);


        int i = 0;
        Cursor fila = BaseDatos.rawQuery("select * from libros where us_libro="+id_usuario,null);
        while (i < numRows){
            if(fila.moveToPosition(i)){
                consulta_nLibro += fila.getString(1)+"  "+"\n";
                consulta_gLibro += fila.getString(2)+"  "+"\n";
                consulta_iLibro += fila.getString(3)+"  "+"\n";
                BaseDatos.close();
                // Toast.makeText(this, "id usuario:  " + id_usuario, Toast.LENGTH_SHORT).show();
            }
            i++;
        }


        Intent pag_verLibros = new Intent(this, verLibros.class);
        pag_verLibros.putExtra("dato", consulta_nLibro);
        pag_verLibros.putExtra("dato2", consulta_gLibro);
        pag_verLibros.putExtra("dato3", consulta_iLibro);
        startActivity(pag_verLibros);


    }
    public void eliminarLibro(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        Intent eli_libro = new Intent(this, eliminarLibros.class);
        eli_libro.putExtra("dato",id_usuario);
        startActivity(eli_libro);
    }
}
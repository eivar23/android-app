 package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


 public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TableLayout tb1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
    public void verListadoAnime(View view){
        int id_usuario = getIntent().getIntExtra("dato",0);
        String consulta_des = "";
        String consulta_tipo = "";

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM anime", null);


        int i = 0;
        Cursor fila = BaseDatos.rawQuery("select * from anime where us_anime="+id_usuario,null);
        while (i < numRows){
            if(fila.moveToPosition(i)){
                //id_usuario = Integer.parseInt(fila.getString(0));
                consulta_des += fila.getString(1)+"  "+"\n";
                consulta_tipo += fila.getString(2)+"  "+"\n";
                BaseDatos.close();
               // Toast.makeText(this, "id usuario:  " + id_usuario, Toast.LENGTH_SHORT).show();
            }
            i++;
        }


        Intent pag_verAnime = new Intent(this, verAnime.class);
        pag_verAnime.putExtra("dato", consulta_des);
        pag_verAnime.putExtra("dato2", consulta_tipo);
        startActivity(pag_verAnime);


    }
     public void ingresarAnime(View view){
         int id_usuario = getIntent().getIntExtra("dato",0);
         Intent pag_anime = new Intent(this, ingresarAnime.class);
         pag_anime.putExtra("dato",id_usuario);
         startActivity(pag_anime);
     }
     public void eliminarAnime(View view){
         int id_usuario = getIntent().getIntExtra("dato",0);
         Intent eli_anime = new Intent(this, eliminarAnime.class);
         eli_anime.putExtra("dato",id_usuario);
         startActivity(eli_anime);
     }



 }
package com.example.listasbd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class eliminarLibros extends AppCompatActivity {
    Spinner spLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_libros);
        spLibro = (Spinner) findViewById(R.id.sp_libros1);
        int id_usuario = getIntent().getIntExtra("dato", 0);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM libros where us_libro=" + id_usuario, null);
        String[] listaLibro = new String[numRows];
        int i = 0;
        Cursor fila = BaseDatos.rawQuery("select * from libros where us_libro=" + id_usuario, null);
        while (i < numRows) {
            if (fila.moveToPosition(i)) {
                listaLibro[i] = fila.getString(1);
                BaseDatos.close();

            }
            i++;

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLibro);
            spLibro.setAdapter(adapter);
        }

    }

    public void eliminarL(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        try {
            String N_libro = spLibro.getSelectedItem().toString();

            int cantidad = BaseDatos.delete("libros","nombre='"+N_libro+"'", null );
            BaseDatos.close();
            if (cantidad == 1){
                Toast.makeText(this, "El libro ha sido eliminado" ,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "selecciona un valor de la lista", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "No hay registros para eliminar", Toast.LENGTH_SHORT).show();
        }

    }
}
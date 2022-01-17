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

public class eliminarCancion extends AppCompatActivity {
    Spinner spCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_cancion);

        spCancion = (Spinner) findViewById(R.id.sp_cancion);
        int id_usuario = getIntent().getIntExtra("dato", 0);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        int numRows = (int) DatabaseUtils.longForQuery(BaseDatos, "SELECT COUNT(*) FROM canciones where us_cancion=" + id_usuario, null);
        String[] listaCancion = new String[numRows];
        int i = 0;
        Cursor fila = BaseDatos.rawQuery("select * from canciones where us_cancion=" + id_usuario, null);
        while (i < numRows) {
            if (fila.moveToPosition(i)) {
                listaCancion[i] = fila.getString(1);
                BaseDatos.close();

            }
            i++;

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCancion);
            spCancion.setAdapter(adapter);
        }

    }

    public void eliminarC(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        try {
            String N_cancion = spCancion.getSelectedItem().toString();
            int cantidad = BaseDatos.delete("canciones","nombre='"+N_cancion+"'", null );
            BaseDatos.close();
            if (cantidad == 1){
                Toast.makeText(this, "la canción ha sido eliminada" ,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "selecciona un valor de la lista", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "No hay registros para eliminar", Toast.LENGTH_SHORT).show();
        }

    }
}
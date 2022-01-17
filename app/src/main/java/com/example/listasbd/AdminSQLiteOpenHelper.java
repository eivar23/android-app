package com.example.listasbd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDatos) {
        baseDatos.execSQL(
                "create table anime(anime_id integer primary key autoincrement,descripcion text, tipo text, us_anime integer)");
        baseDatos.execSQL(
                "create table usuario(user_id integer primary key autoincrement, user text, correo text, pass string)");
        baseDatos.execSQL(
                "create table canciones(cancion_id integer primary key autoincrement, nombre text, genero text, dificultad integer, us_cancion integer)");
        baseDatos.execSQL(
                "create table libros(libro_id integer primary key autoincrement, nombre text, genero text, idioma text, us_libro integer)");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

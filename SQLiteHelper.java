package com.example.pedri.elavamonos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String nome, String tempo, String rendimento,  String ingredientes,String preparo ,byte[] foto){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO RECEITA VALUES (NULL, ?, ?, ?,? ,? , ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nome);
        statement.bindString(2, tempo);
        statement.bindString(3, rendimento);
        statement.bindString(4, ingredientes);
        statement.bindString(5, preparo);
        statement.bindBlob(6, foto);

        statement.executeInsert();
    }

    public void updateData(String nome, String tempo, String rendimento,  String ingredientes,String preparo ,byte[] foto,  int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE RECEITA SET nome = ?, tempo = ?,  rendimento = ?, ingredientes = ?, preparo = ? ,foto = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nome);
        statement.bindString(2, tempo);
        statement.bindString(3, rendimento);
        statement.bindString(4, ingredientes);
        statement.bindString(5, preparo);
        statement.bindBlob(6, foto);
        statement.bindDouble(7, (double)id);
        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM RECEITA WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

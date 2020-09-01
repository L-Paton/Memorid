package com.xnea.memorid.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xnea.memorid.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Sentencias {

    private DataBase db;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public Sentencias(Context context){
        this.context = context;
    }

    public void addCard(String word, String description){
        db  = new DataBase(context);
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO MEMORID_DB VALUES('"+ word +"','"+description+"',0,0)");

        sqLiteDatabase.close();
        db.close();
    }

    public List<Card> getCards(){
        List<Card> arrayList = new ArrayList<>();
        db  = new DataBase(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT PALABRA, DESCRIPCION, ACIERTOS, FALLOS FROM MEMORID_DB", null);

        while(c.moveToNext()){
            String word = c.getString(0);
            String description = c.getString(1);
            int aciertos = c.getInt(2);
            int fallos = c.getInt(3);
            Card card = new Card(word, description, aciertos, fallos);
            arrayList.add(card);
        }

        c.close();
        sqLiteDatabase.close();
        db.close();

        return arrayList;
    }

    public void updatePoints(Card card){
        db  = new DataBase(context);
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE MEMORID_DB SET ACIERTOS = " + card.getAciertos() + ", FALLOS = " + card.getFallos() + " WHERE PALABRA = '" + card.getWord() + "'");
        sqLiteDatabase.close();
        db.close();
    }

    public Card getRamdonCard(){
        Card card = null;
        db  = new DataBase(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT PALABRA, DESCRIPCION, ACIERTOS, FALLOS FROM MEMORID_DB ORDER BY RANDOM() LIMIT 1", null);
        if(c.moveToNext()){
            String palabra = c.getString(0);
            String descripcion = c.getString(1);
            int aciertos = c.getInt(2);
            int fallos = c.getInt(3);
            card = new Card(palabra, descripcion, aciertos, fallos);
        }

        c.close();
        sqLiteDatabase.close();
        db.close();

        return card;
    }

    public void deleteCard(String word){
        db = new DataBase(context);
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM MEMORID_DB WHERE PALABRA = '" + word + "'");
        sqLiteDatabase.close();
        db.close();
    }
}

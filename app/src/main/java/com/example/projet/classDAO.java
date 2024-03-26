package com.example.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class classDAO {
    private SQLiteDatabase db;

    public classDAO(Context context) {
        SQLHelper dbHelper = new SQLHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long ajouterItem(ItemClass item) {
        ContentValues values = new ContentValues();
        values.put("NumMat", item.getNumMat());
        values.put("Design", item.getDesign());
        values.put("Etat", item.getEtat());
        values.put("Quantite", item.getQuantite());
        return db.insert("table_item", null, values);
    }

    public ArrayList<ItemClass> getAllItems() {
        ArrayList<ItemClass> itemList = new ArrayList<>();
        Cursor cursor = db.query("table_item", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            ItemClass item = new ItemClass();
            item.setNumMat(cursor.getString(cursor.getColumnIndexOrThrow("NumMat")));
            item.setNom(cursor.getString(cursor.getColumnIndexOrThrow("Design")));
            item.setEtat(cursor.getString(cursor.getColumnIndexOrThrow("Etat")));
            item.setQuantite(cursor.getInt(cursor.getColumnIndexOrThrow("Quantite")));
            System.out.println(cursor.getString(cursor.getColumnIndexOrThrow("Design")));
            itemList.add(item);
        }

        cursor.close();
        return itemList;
    }
    public int modifierItem(ItemClass item) {
        ContentValues values = new ContentValues();
        values.put("Design", item.getDesign());
        values.put("Etat", item.getEtat());
        values.put("Quantite", item.getQuantite());

        String whereClause = "NumMat = ?";
        String[] whereArgs = {item.getNumMat()};

        return db.update("table_item", values, whereClause, whereArgs);
    }
    public void supprimerItem(String numMat) {
        String whereClause = "NumMat = ?";
        String[] whereArgs = {numMat};
        db.delete("table_item", whereClause, whereArgs);
    }


    public int getTotalQuantite() {
        Cursor cursor = db.rawQuery("SELECT SUM(Quantite) FROM table_item", null);
        float totalQuantite = 0;
        if (cursor.moveToFirst()) {
            totalQuantite = cursor.getFloat(0);
        }
        cursor.close();
        return (int) totalQuantite;
    }

    public int getTotalQuantiteByEtat(String etat) {
        // Utilisez le paramètre etat pour filtrer les résultats selon l'état spécifié
        Cursor cursor = db.rawQuery("SELECT SUM(Quantite) FROM table_item WHERE Etat = ?", new String[]{etat});
        float totalQuantite = 0;
        if (cursor.moveToFirst()) {
            totalQuantite = cursor.getFloat(0);
        }
        cursor.close();
        return (int) totalQuantite;
    }

}


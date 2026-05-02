package projet.fst.ma.app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.util.DatabaseHelper;

public class EtudiantService {

    private static final String TABLE   = "etudiants";
    private static final String COL_ID  = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRE = "prenom";

    private final DatabaseHelper dbHelper;

    public EtudiantService(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    // --- Ajouter ---
    public void create(Etudiant etudiant) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOM, etudiant.getNom());
        cv.put(COL_PRE, etudiant.getPrenom());
        db.insert(TABLE, null, cv);
        db.close();
    }

    // --- Chercher par ID ---
    public Etudiant findById(int searchId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE,
                new String[]{COL_ID, COL_NOM, COL_PRE},
                COL_ID + " = ?",
                new String[]{String.valueOf(searchId)},
                null, null, null
        );

        Etudiant result = null;
        if (cursor.moveToFirst()) {
            result = cursorToEtudiant(cursor);
        }
        cursor.close();
        db.close();
        return result;
    }

    // --- Lister tous ---
    public List<Etudiant> findAll() {
        List<Etudiant> liste = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                liste.add(cursorToEtudiant(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return liste;
    }

    // --- Supprimer ---
    public void delete(Etudiant etudiant) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE, COL_ID + " = ?",
                new String[]{String.valueOf(etudiant.getId())});
        db.close();
    }

    // --- Mettre à jour ---
    public void update(Etudiant etudiant) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOM, etudiant.getNom());
        cv.put(COL_PRE, etudiant.getPrenom());
        db.update(TABLE, cv, COL_ID + " = ?",
                new String[]{String.valueOf(etudiant.getId())});
        db.close();
    }

    // Helper privé
    private Etudiant cursorToEtudiant(Cursor c) {
        Etudiant e = new Etudiant();
        e.setId(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
        e.setNom(c.getString(c.getColumnIndexOrThrow(COL_NOM)));
        e.setPrenom(c.getString(c.getColumnIndexOrThrow(COL_PRE)));
        return e;
    }
}
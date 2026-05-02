package projet.fst.ma.app.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME    = "school.db";
    private static final int    DB_VERSION = 1;

    private static final String CREATE_ETUDIANTS =
            "CREATE TABLE IF NOT EXISTS etudiants (" +
                    "id      INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom     TEXT    NOT NULL, " +
                    "prenom  TEXT    NOT NULL);";

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ETUDIANTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS etudiants");
        onCreate(db);
    }
}
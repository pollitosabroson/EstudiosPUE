package es.pue.sergi.shapp.service.backup.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daa on 27/02/2017.
 */

public class TiendaSqliteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="Tiendas_DB";
    public static final int DB_VERSION=1;

    public static final String TIENDAS_TABLE="tiendas",
            COLUMN_ID="_id", COLUMN_NAME="name", COLUMN_PRICE="price",
            COLUMN_SERVICE="servicio", COLUMN_WEB="web", COLUMN_TEL="tel", COLUMN_RATING="rating",
            COLUMN_LATITUDE="latitude", COLUMN_LONGITUDE="longitude";
    public TiendaSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TIENDAS_TABLE + "( "+
                COLUMN_ID + " integer primary key autoincrement, "+
                COLUMN_NAME + "TEXT" +
                COLUMN_PRICE+ "int" +
                COLUMN_RATING + "int" +
                COLUMN_SERVICE + "int" +
                COLUMN_TEL + "text" +
                COLUMN_WEB + "text" +
                COLUMN_LATITUDE + "real" +
                COLUMN_LONGITUDE + "real"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion <= 1){
            db.execSQL("ALTER TABLE" + TIENDAS_TABLE + "ADD" + COLUMN_LATITUDE + "real");
            db.execSQL("ALTER TABLE" + TIENDAS_TABLE + "ADD" + COLUMN_LONGITUDE + "real");
        }
    }
}

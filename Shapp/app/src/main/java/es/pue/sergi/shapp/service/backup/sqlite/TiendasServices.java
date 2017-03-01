package es.pue.sergi.shapp.service.backup.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.pue.sergi.shapp.model.Tienda;
import es.pue.sergi.shapp.service.TiendasService;
import es.pue.sergi.shapp.service.backup.sqlite.TiendaSqliteOpenHelper;

/**
 * Created by daa on 27/02/2017.
 */

class SqliteTiendasService  implements TiendasService{

    TiendaSqliteOpenHelper openHelper;

    public SqliteTiendasService (Context context){
        openHelper = new TiendaSqliteOpenHelper(context);
    }
    @Override
    public List<Tienda> getAllTiendas() {
        List <Tienda> tiendas = new ArrayList<>();

        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor c = db.query(
                TiendaSqliteOpenHelper.TIENDAS_TABLE,
                null, null, null, null, null, null
        );
        if (c.moveToFirst()) {
            do {
                tiendas.add(getTiendaFromcursor(c));
            }while (c.moveToNext());
            c.close();
            return tiendas;
        }
        c.close();
        return null;
    }

    @Override
    public Tienda getTienda(long id) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.query(
                TiendaSqliteOpenHelper.TIENDAS_TABLE, null,
                TiendaSqliteOpenHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null
                );

        if (cursor.moveToFirst()){
            cursor.close();
            return getTiendaFromcursor(cursor);
        }
        cursor.close();
        return null;
    }

    private Tienda getTiendaFromcursor(Cursor cursor){
        long id = cursor.getLong(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_NAME));
        int servicio = cursor.getInt(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_SERVICE));
        int price = cursor.getInt(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_PRICE));
        int rating = cursor.getInt(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_RATING));
        String web = cursor.getString(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_TEL));
        String tel = cursor.getString(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_WEB));
        float lat = cursor.getFloat(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_LATITUDE));
        float lon = cursor.getFloat(cursor.getColumnIndex(TiendaSqliteOpenHelper.COLUMN_LONGITUDE));

        Tienda result = new Tienda();
        result.set_id(id);
        result.setNombre(name);
        result.setTelefono(tel);
        result.setPrecio(price);
        result.setRating(rating);
        result.setServicio(servicio);
        result.setWeb(web);
        return result;
    }

    @Override
    public void removeTienda(long id) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        int totalDeleted = db.delete(
                TiendaSqliteOpenHelper.TIENDAS_TABLE,
                TiendaSqliteOpenHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

    }

    @Override
    public void saveTienda(Tienda t) {
        SQLiteDatabase db =openHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(TiendaSqliteOpenHelper.COLUMN_NAME, t.getNombre());
        values.put(TiendaSqliteOpenHelper.COLUMN_PRICE, t.getPrecio());
        values.put(TiendaSqliteOpenHelper.COLUMN_SERVICE, t.getServicio());
        values.put(TiendaSqliteOpenHelper.COLUMN_RATING, t.getRating());
        values.put(TiendaSqliteOpenHelper.COLUMN_TEL, t.getTelefono());
        values.put(TiendaSqliteOpenHelper.COLUMN_WEB, t.getWeb());
        values.put(TiendaSqliteOpenHelper.COLUMN_LATITUDE, t.getLatitude());
        values.put(TiendaSqliteOpenHelper.COLUMN_LONGITUDE, t.getLongitude());

        if (t.get_id() == 0){
            long tienda_id = db.insert(TiendaSqliteOpenHelper.TIENDAS_TABLE, null, values);
            if (tienda_id < 0) {
                Log.e("SQLite tiendas service", "Error");
            }else{
                t.set_id(tienda_id);
            }
        }else{
            db.update(TiendaSqliteOpenHelper.TIENDAS_TABLE,
                    values, TiendaSqliteOpenHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(t.get_id())});
        }
    }

    public void DeleteAllTiendas(List<Tienda> tiendas){
        SQLiteDatabase db = openHelper.getWritableDatabase();

        db.beginTransaction();

        db.delete(TiendaSqliteOpenHelper.TIENDAS_TABLE, null, null);

        Cursor total = db.query(TiendaSqliteOpenHelper.TIENDAS_TABLE, null, null, null, null, null, null);
        if (total.getCount() ==0){
            db.setTransactionSuccessful();
        }

        db.endTransaction();
    }
}

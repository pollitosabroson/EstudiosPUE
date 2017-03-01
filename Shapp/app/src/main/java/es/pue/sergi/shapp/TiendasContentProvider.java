package es.pue.sergi.shapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import es.pue.sergi.shapp.service.backup.sqlite.TiendaSqliteOpenHelper;

public class TiendasContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "es.pue.sergi.shapp";
    public static final int MATCH_ALL_TIENDAS = 1;
    public static final int MATCH_ONE_TIENDAS = 2;


    TiendaSqliteOpenHelper openHelper;
    UriMatcher uriMatcher;
    SQLiteDatabase db;
    public TiendasContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        boolean result = false;
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "tiendas", MATCH_ALL_TIENDAS);
        uriMatcher.addURI(PROVIDER_NAME, "tiendas/*", MATCH_ONE_TIENDAS);
        openHelper = new TiendaSqliteOpenHelper(getContext());
         db = openHelper.getWritableDatabase();
        if (db != null) result= true;
        return result;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case MATCH_ALL_TIENDAS:
                return db.query(
                        TiendaSqliteOpenHelper.TIENDAS_TABLE, projection,
                        selection,selectionArgs,null,null,sortOrder);
            case MATCH_ONE_TIENDAS:
                return  db.query(
                        TiendaSqliteOpenHelper.TIENDAS_TABLE, projection,
                        TiendaSqliteOpenHelper.COLUMN_ID + "=?",
                        new String[]{uri.getLastPathSegment()},
                        null,null,null);
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

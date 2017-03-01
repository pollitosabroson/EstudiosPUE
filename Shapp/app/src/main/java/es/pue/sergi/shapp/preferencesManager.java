package es.pue.sergi.shapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by daa on 20/02/2017.
 */

public class preferencesManager {
    Context context;
    public final static String PREFS_FILE="shapp_backup_preferences";
    public final static String BACKUP_EXTERNAL="backup_external";
    public final static String BACKUP_CLOUD="shapp_backup_cloud";
    public final static String BACKUP_WEEKLY="backup_external";
    public preferencesManager(Context c){
        this.context = c;
    }

    public boolean isExternalBackupEnable(){
        return context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE).getBoolean(BACKUP_EXTERNAL, false);
    }

    public void setExternalBackupEnable(boolean enable){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BACKUP_EXTERNAL, enable);
        editor.commit();
    }

}

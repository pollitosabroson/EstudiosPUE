package es.pue.sergi.shapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import es.pue.sergi.shapp.BackupActivity;

/**
 * Created by daa on 20/02/2017.
 */
public class PreferencesManager {

    public final static String PREFS_FILE="shapp_backup_preferences";

    public final static String BACKUP_EXTERNAL="backup_external";
    public final static String BACKUP_CLOUD="backup_cloud";
    public final static String BACKUP_FREQ="backup_freq";
    public final static String PREF_BACKUP_URL = "pref_BACKUP_URL";

    Context context;

    public PreferencesManager(Context c){
        this.context=c;
    }

    public boolean isExternalBackupEnabled(){
        return context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE).getBoolean(BACKUP_EXTERNAL,false);
    }

    public void setExternalBackupEnabled(boolean enabled){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(BACKUP_EXTERNAL,enabled);
        editor.commit();
    }

    public boolean isCloudBackupEnabled() {
        return context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE)
                .getBoolean(BACKUP_CLOUD,false);
    }

    public int getBackupFrequency() {
        return context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE)
                .getInt(BACKUP_FREQ, BackupActivity.BACKUP_FREQUENCY_DAILY);
    }


    public void setCloudBackupEnabled(boolean checked) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(BACKUP_CLOUD,checked);
        editor.commit();
    }

    public void setBackupFrequency(int backupFrequency) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(BACKUP_FREQ,backupFrequency);
        editor.commit();
    }


    public String getCloudbackupUrl(){
        // Recuperamos la url del backup
        return context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE).
                getString(BACKUP_FREQ, "");
    }

    public void setCloudBackupUrl(String cloudBackupUrl){
        // Grabamos la url donde se ha guardado el backup
        SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BACKUP_FREQ, cloudBackupUrl);
        editor.commit();
    }

}

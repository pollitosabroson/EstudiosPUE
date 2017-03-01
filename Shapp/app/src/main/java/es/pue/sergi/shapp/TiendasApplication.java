package es.pue.sergi.shapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import es.pue.sergi.shapp.preferences.PreferencesManager;
import es.pue.sergi.shapp.service.InMemoryTiendasService;
import es.pue.sergi.shapp.service.InternalStorageTiendasService;
import es.pue.sergi.shapp.service.TiendasService;
import es.pue.sergi.shapp.service.backup.BackupIntentService;
import es.pue.sergi.shapp.service.backup.BackupService;

/**
 * Created by daa on 06/02/2017.
 */

public class TiendasApplication extends Application {

    private TiendasService tiendasService;
    private PreferencesManager preferencesManager;
    private static TiendasApplication mInstance;
    private BroadcastReceiver shopNearNotificationReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application","Creating application class");
        mInstance=this;
        tiendasService=new InternalStorageTiendasService(this);
        preferencesManager=new PreferencesManager(this);
        boolean autobackup = true;
        if (autobackup){
            Intent i = new Intent(this, BackupIntentService.class);
            startService(i);
        }

        Intent i = new Intent(this, LocationUpdateService.class);
        startService(i);

        shopNearNotificationReceiver =   new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long tiendasid[] = intent.getExtras().getLongArray("Shop tiendas");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(TiendasApplication.this);
                builder.setContentTitle("Location");
                builder.setSmallIcon(R.drawable.ic_menu_camera);
                assert tiendasid != null;
                builder.setContentText("There are " + tiendasid.length + " Shop near" );
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(
                shopNearNotificationReceiver,
                new IntentFilter(LocationUpdateService.SHOP_NEAR_BROADCASTER)
        );
    }

    @Override
    public void onTerminate() {
        Intent i = new Intent(this, LocationUpdateService.class);
        startService(i);
        super.onTerminate();
    }

    public static TiendasApplication getInstance(){
        return mInstance;
    }
    public TiendasService getTiendasService(){
        return tiendasService;
    }
    public PreferencesManager getPreferencesManager(){return preferencesManager;}
    public BackupService getBackupService() {
        return null;
    }
}

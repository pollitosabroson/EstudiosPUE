package es.pue.sergi.shapp.service.backup;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;

import es.pue.sergi.shapp.TiendasApplication;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class BackupIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "es.pue.sergi.shapp.service.backup.action.FOO";
    public static final String ACTION_BAZ = "es.pue.sergi.shapp.service.backup.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "es.pue.sergi.shapp.service.backup.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "es.pue.sergi.shapp.service.backup.extra.PARAM2";

    public BackupIntentService() {
        super("BackupIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        BackupService backupService = TiendasApplication.getInstance().getBackupService();
        if (backupService.isBackupReady()){
            Handler toasthandler = new Handler(Looper.getMainLooper()){

                private int notificaciones = 10;
                @Override
                public void handleMessage(Message msg) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(BackupIntentService.this);
                    builder.setContentTitle("Shapp Backup");
                    builder.setSmallIcon(android.R.drawable.arrow_up_float);
                    builder.setContentText(msg.getData().getString(CloudBackupService.HANDLER_BUNDLE_STATUS));

                    NotificationManager ntm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    ntm.notify(notificaciones, builder.build());
                    /*Toast.makeText(BackupIntentService.this, msg.getData().getString(CloudBackupService.HANDLER_BUNDLE_STATUS), Toast.LENGTH_LONG);
                    super.handleMessage(msg);*/
                }
            };
            try {
                backupService.doBackup(toasthandler);
            }catch (TiendasBackupException e){
                toasthandler.sendMessage(CloudBackupService.createMessage("Error performing backup"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

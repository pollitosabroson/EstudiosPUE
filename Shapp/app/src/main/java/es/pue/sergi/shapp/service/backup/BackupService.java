package es.pue.sergi.shapp.service.backup;

import android.os.Handler;

import java.io.IOException;

/**
 * Created by daa on 22/02/2017.
 */

public interface BackupService {
    boolean isBackupReady();
    void doBackup(Handler uiHandler) throws TiendasBackupException, IOException;
    boolean isRestoreReady();
    void doRestore(Handler uiHandler) throws TiendasBackupException;
}

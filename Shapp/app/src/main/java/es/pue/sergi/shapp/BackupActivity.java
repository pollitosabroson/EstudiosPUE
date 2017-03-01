package es.pue.sergi.shapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class BackupActivity extends AppCompatActivity {

    public static final int BACKUP_FREQUENCY_DAILY=1;
    public static final int BACKUP_FREQUENCY_WEEKLY=2;

    CheckBox external;
    CheckBox cloud;
    RadioButton daily;
    RadioButton weekly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);

        external=(CheckBox)findViewById(R.id.backup_config_external);
        cloud=(CheckBox)findViewById(R.id.backup_config_cloud);
        daily=(RadioButton)findViewById(R.id.backup_config_daily);
        weekly=(RadioButton)findViewById(R.id.backup_config_weekly);
        external.setChecked(
                TiendasApplication.getInstance().getPreferencesManager().isExternalBackupEnabled()
        );
        cloud.setChecked(
                TiendasApplication.getInstance().getPreferencesManager().isCloudBackupEnabled()
        );
        switch (TiendasApplication.getInstance().getPreferencesManager().getBackupFrequency()){
            case BACKUP_FREQUENCY_DAILY:
                weekly.setChecked(false);
                daily.setChecked(true);
                break;
            case BACKUP_FREQUENCY_WEEKLY:
                weekly.setChecked(true);
                daily.setChecked(false);
                break;
            default:
                weekly.setChecked(false);
                daily.setChecked(false);
                break;
        }
    }

    public void toggleExternal(View v){
        TiendasApplication.getInstance().getPreferencesManager().setExternalBackupEnabled(external.isChecked());
    }

    public void toggleCloud(View v){
        TiendasApplication.getInstance().getPreferencesManager().setCloudBackupEnabled(cloud.isChecked());
    }

    public void setDaily(View v){
        TiendasApplication.getInstance().getPreferencesManager().setBackupFrequency(BACKUP_FREQUENCY_DAILY);
    }
    public void setWeekly(View v){
        TiendasApplication.getInstance().getPreferencesManager().setBackupFrequency(BACKUP_FREQUENCY_WEEKLY);
    }
}

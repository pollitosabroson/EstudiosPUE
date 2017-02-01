package es.pue.linterna.linterna;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton mToogleButton;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean hasFlash = hasflash();

        if (!hasFlash){
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("No tienes flash");
            alert.setMessage("Compra un mejor telefono, pobre!!!!!");
            alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mToogleButton=(ToggleButton)findViewById(R.id.toggleButton);
        mToogleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    turnOnFlash();
                }else{
                    turnOffFlash();
                }
            }
        });
    }

    private Boolean hasflash(){
        return (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH));
    }

    private void turnOnFlash(){
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();
    }

    private void turnOffFlash(){
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
        camera.startPreview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LINTERNAT", "EN EL RESUMEN");
        if (camera == null){
            camera=Camera.open();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LINTERNAT", "EN EL START");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LINTERNAT", "EN EL PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LINTERNAT", "EN EL STOP");
        if (camera != null){
            camera.release();
            camera = null;
        }
    }
}

package es.pue.sergi.shapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.pue.sergi.shapp.model.Tienda;

/**
 * Created by daa on 01/03/2017.
 */

public class LocationUpdateService extends Service {
    private Location lastLocation;
    private LocationListener listener;
    private static final  int LOCATION_NOTIFICATION=0;
    public static final  String SHOP_NEAR_BROADCASTER="test";

    public LocationUpdateService() {
        listener = new DeCompreasListener();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        onStartLocationUpdates();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopLocationUpdate();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void onStartLocationUpdates() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 20, listener);
    }

    private void stopLocationUpdate(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            manager.removeUpdates(listener);
            NotificationCompat.Builder builder =  new NotificationCompat.Builder(this);
            builder.setContentTitle("Location");
            builder.setSmallIcon(R.drawable.ic_menu_camera);
            builder.setContentText("Location start");
        }catch (SecurityException e){

        }
    }

    private void checkShopNear(){
        List<Tienda> tienda = TiendasApplication.getInstance().getTiendasService().getAllTiendas();
        List<Long> tiendasNearIds = new ArrayList<>();
        for (Tienda t:tienda){
            Location tiendaLoc = new Location("");
            tiendaLoc.setLatitude(t.getLatitude());
            tiendaLoc.setLatitude(t.getLongitude());

            if (lastLocation.distanceTo(tiendaLoc) < 200){
                tiendasNearIds.add(t.get_id());
            }
            if (!tiendasNearIds.isEmpty()){
                Intent i = new Intent(SHOP_NEAR_BROADCASTER);
                long[] lista = new long[tiendasNearIds.size()];
                int j = 0;
                for (long id:tiendasNearIds){
                    lista[j] = id;
                    j++;
                }
                i.putExtra("Shop near",lista);
                LocalBroadcastManager.getInstance(this).sendBroadcast(i);
            }
        }
    }

    private class DeCompreasListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
           Log.i("Location", String.valueOf(location.getAltitude()));
            if (lastLocation == null){
                lastLocation = location;
            }else {
                if (location.getAccuracy() < lastLocation.getAccuracy()){
                    lastLocation = location;
                }else if (location.getAccuracy() < 20){
                    lastLocation = location;
                }else if (location.getTime() + 30*1000 < System.currentTimeMillis()){
                    lastLocation = location;
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}

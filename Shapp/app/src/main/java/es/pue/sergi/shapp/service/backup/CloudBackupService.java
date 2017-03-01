package es.pue.sergi.shapp.service.backup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import es.pue.sergi.shapp.TiendasApplication;


/**
 * Created by daa on 22/02/2017.
 */

public class CloudBackupService implements BackupService {

    public static final  String HANDLER_BUNDLE_STATUS = "handler_message";
    public static final String DEFAULT_URL = "https://api.myjson.com/bins";
    private Context context;
    public CloudBackupService(Context c){
        context = c;
    }
    private boolean hasConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info.isConnected())return true;
        return false;
    }
    @Override
    public boolean isBackupReady() {
        return hasConnectivity();
    }

    @Override
    public void doBackup(Handler uiHandler) throws TiendasBackupException, IOException {
        String url = TiendasApplication.getInstance().getPreferencesManager().getCloudbackupUrl();
        String method =  "PUT";
        if (url.isEmpty()){
            method = "POST";
            url=DEFAULT_URL;
        };
        uiHandler.sendMessage(createMessage("Preparing connection"));
         URL u = null;
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(30000);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        uiHandler.sendMessage(createMessage("create JSOM"));
        Gson gson = new Gson();
        String json = gson.toJson(TiendasApplication.getInstance().getTiendasService().getAllTiendas());
        OutputStreamWriter out =  new OutputStreamWriter(connection.getOutputStream());
        out.write(json);
        out.flush();

        int response_code = connection.getResponseCode();
        if (response_code == 201 || response_code == 200){
            if (response_code == 201){
                JsonReader jreader = new JsonReader(new InputStreamReader(connection.getInputStream()));
                jreader.beginObject();
                if (jreader.hasNext()){
                    jreader.nextName();
                    String backupURI = jreader.nextString();
                    TiendasApplication.getInstance().getPreferencesManager().setCloudBackupUrl(backupURI);
                }
            }
        }else {
            throw new TiendasBackupException();
        }
    }

    @Override
    public boolean isRestoreReady() {
        return true;
    }

    @Override
    public void doRestore(Handler uiHandler) throws TiendasBackupException {
        for (int i=0; i<5; i++){
            uiHandler.sendMessage(createMessage("step "+i));
            try{Thread.sleep(3000);}catch (InterruptedException e){};
        };
    }

    public static Message createMessage(String message){
        Message result = new Message();
        Bundle data = new Bundle();
        data.putString(HANDLER_BUNDLE_STATUS, message);
        result.setData(data);
        return result;
    };
}

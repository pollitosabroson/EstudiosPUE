package es.pue.sergi.shapp.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.pue.sergi.shapp.model.Tienda;
import es.pue.sergi.shapp.service.TiendasService;

/**
 * Created by daa on 20/02/2017.
 */

public class InternlStorageTiendasService implements TiendasService {

    public static final String FILE_NAME="tiendas_file";

    Map<Long, Tienda> tiendas;
    Context context;

    public InternlStorageTiendasService(Context context){
        this.context = context;
        loadAlltiendas();
    }

    private void saveAlltiendas(){
        OutputStream os = null;
        OutputStreamWriter osw = null;
        String toWrite = "";
        Gson gson = new Gson();

        try{
            toWrite = gson.toJson(tiendas);
            os = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(os);
            osw.write(toWrite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadAlltiendas(){
        BufferedReader reader = null;
        InputStream is = null;
        InputStreamReader isReader = null;

        try{
            is = context.openFileInput(FILE_NAME);
            isReader = new InputStreamReader(is);
            reader = new BufferedReader(isReader);
            StringBuilder sb = new StringBuilder();
            String temp ="";

            while ((temp = reader.readLine()) != null){
                sb.append(temp);
            }
            /*Convertir  Strng a MAP */
            Gson gson = new Gson();
            tiendas = gson.fromJson(sb.toString(), new TypeToken<Map<Long, Tienda>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Override
    public void saveTienda(Tienda t) {

    }

    @Override
    public List<Tienda> getAllTiendas() {
        return new ArrayList<>(tiendas.values());
    }

    @Override
    public Tienda getTienda(long id) {
        return tiendas.get(id);
    }

    @Override
    public void removeTienda(long id) {
        tiendas.remove(id);
        saveAlltiendas();
    }
}

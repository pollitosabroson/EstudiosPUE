package es.pue.sergi.shapp.service;

import android.content.Context;
import android.util.Log;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.pue.sergi.shapp.model.Tienda;

/**
 * Created by daa on 20/02/2017.
 */

public class InternalStorageTiendasService implements TiendasService {

    public static final String FILE_NAME="tiendas_file";

    Map<Long,Tienda> tiendas;
    Context context;

    public InternalStorageTiendasService(Context context){
        this.context=context;
        loadAllTiendas();
        if(tiendas==null)tiendas=new LinkedHashMap<>();
        if(tiendas.isEmpty()){
            Tienda t = new Tienda();
            t.set_id(getNextId());
            t.setNombre("Bambas el hobbit");
            t.setRating(6);
            t.setServicio(7);
            t.setPrecio(5);
            t.setWeb("http://www.hobbitbambas.com");
            saveTienda(t);
        }
    }

    private void saveAllTiendas(){
        OutputStream os=null;
        OutputStreamWriter osw=null;
        String toWrite="";
        Gson gson=new Gson();
        try{
            toWrite=gson.toJson(tiendas);
            os=context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            osw=new OutputStreamWriter(os);
            osw.write(toWrite);
        } catch (FileNotFoundException e) {
            Log.e("InternalStorage","Error file not found",e);
        } catch (IOException e) {
            Log.e("InternalStorage","Error writing to file",e);
        }finally{
            if(osw!=null){
                try{
                    osw.close();
                }catch(IOException e){
                    Log.e("InternalStorage","Error closing file",e);
                }
            }
        }
    }

    private void loadAllTiendas(){
        BufferedReader reader=null;
        InputStream is=null;
        InputStreamReader isReader=null;

        try{
            is=context.openFileInput(FILE_NAME);
            isReader=new InputStreamReader(is);
            reader=new BufferedReader(isReader);

            StringBuilder sb=new StringBuilder();
            String temp="";

            while((temp=reader.readLine())!=null){
                sb.append(temp);
            }

            Gson gson=new Gson();
            tiendas=gson.fromJson(sb.toString(),new TypeToken<Map<Long,Tienda>>(){}.getType());

        }catch(FileNotFoundException e){
            Log.e("InternalStorage","Error file not found",e);
        }catch(IOException e){
            Log.e("InternalStorage","Error could not read file",e);
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException e){
                    Log.e("InternalStorage","Error could not close file",e);
                }
            }
        }

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
        saveAllTiendas();
    }

    @Override
    public void saveTienda(Tienda t) {
        if(t.get_id()==0){
            t.set_id(getNextId());
        }
        tiendas.put(t.get_id(),t);
        saveAllTiendas();
    }

    long lastId=1;
    private synchronized long getNextId(){
        return lastId++;
    }
}

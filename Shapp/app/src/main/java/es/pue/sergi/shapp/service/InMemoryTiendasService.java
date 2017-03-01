package es.pue.sergi.shapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.pue.sergi.shapp.model.Tienda;

/**
 * Created by daa on 06/02/2017.
 */

public class InMemoryTiendasService implements TiendasService {

    private List<Tienda> tiendas=new ArrayList<>();

    public InMemoryTiendasService() {
        for(int i=0; i<10; i++) {
            Tienda t = new Tienda();
            t.set_id(getNextId());
            t.setNombre("Bambas el hobbit "+i);
            t.setRating(6);
            t.setServicio(7);
            t.setPrecio(5);
            t.setWeb("http://www.hobbitbambas.com");
            tiendas.add(t);
            t = new Tienda();
            t.set_id(getNextId());
            t.setNombre("Electronica manolo "+i);
            t.setRating(4);
            t.setServicio(6);
            t.setPrecio(2);
            t.setTelefono("669999666");
            tiendas.add(t);
            t = new Tienda();
            t.set_id(getNextId());
            t.setNombre("Bolsos la pepa "+i);
            t.setRating(8);
            t.setServicio(9);
            t.setPrecio(6);
            t.setWeb("http://www.pepabolsos.com");
            tiendas.add(t);
        }
    }

    @Override
    public List<Tienda> getAllTiendas() {
        return new ArrayList<>(tiendas);
    }

    @Override
    public Tienda getTienda(long id) {
        for(Tienda t:tiendas){
            if(t.get_id()==id) return t;
        }
        return null;
    }

    @Override
    public void removeTienda(long id) {
        for(Iterator<Tienda> iter=tiendas.iterator();iter.hasNext();){
            Tienda t=iter.next();
            if(t.get_id()==id) iter.remove();
        }
    }

    @Override
    public void saveTienda(Tienda t) {
        if(t.get_id()==0){
            t.set_id(getNextId());
            tiendas.add(t);
        }
    }

    private long lastId=1L;
    private synchronized long getNextId(){
        return lastId++;
    }
}

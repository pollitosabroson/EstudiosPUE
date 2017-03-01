package es.pue.sergi.shapp.service;

import java.util.List;

import es.pue.sergi.shapp.model.Tienda;

/**
 * Created by daa on 06/02/2017.
 */

public interface TiendasService {

    public List<Tienda> getAllTiendas();
    public Tienda getTienda (long id);
    public void removeTienda(long id);
    public void saveTienda(Tienda t);
}

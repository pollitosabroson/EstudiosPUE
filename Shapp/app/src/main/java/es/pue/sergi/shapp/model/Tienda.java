package es.pue.sergi.shapp.model;

/**
 * Created by daa on 06/02/2017.
 */

public class Tienda {

    private long _id;
    private String nombre;
    private int rating;
    private int servicio;
    private int precio;
    private String web;
    private String telefono;
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    private float longitude;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

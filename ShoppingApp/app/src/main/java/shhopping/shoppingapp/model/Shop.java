package shhopping.shoppingapp.model;

/**
 * Created by daa on 06/02/2017.
 */

public class Shop {
    private String mnombre;
    private int mrating;
    private int mservicio;

    public void setMprecio(int mprecio) {
        this.mprecio = mprecio;
    }

    private int mprecio;
    private String telefono;
    private String web;
    private long _id;

    public String getMnombre() {
        return mnombre;
    }

    public String getWeb() {
        return web;
    }

    public int getMprecio() {
        return mprecio;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setMnombre(String mnombre) {
        this.mnombre = mnombre;
    }

    public int getMrating() {
        return mrating;
    }

    public void setMrating(int mrating) {
        this.mrating = mrating;
    }

    public int getMservicio() {
        return mservicio;
    }

    public void setMservicio(int mservicio) {
        this.mservicio = mservicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}

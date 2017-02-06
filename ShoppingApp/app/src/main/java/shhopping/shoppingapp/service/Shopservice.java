package shhopping.shoppingapp.service;

import java.util.List;

import shhopping.shoppingapp.model.Shop;

/**
 * Created by daa on 06/02/2017.
 */

public interface Shopservice {
    public List<Shop> getAlltiendas();
    public Shop getTienda (long id);
    public void removeShop(long id);
    public void saveShop(long id);
}

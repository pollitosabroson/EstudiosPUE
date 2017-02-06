package shhopping.shoppingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import shhopping.shoppingapp.model.Shop;

/**
 * Created by daa on 06/02/2017.
 */

public class InMemoryShoService implements Shopservice{
    private List<Shop> shops = new ArrayList<>();

    @Override
    public List<Shop> getAlltiendas() {
        Shop s = new Shop();
        s.set_id(1);
        s.setMnombre("Nombre 1");
        s.setMprecio(10);
        s.setMrating(5);
        s.setMservicio(7);
        s.setTelefono("123456789");
        s.setWeb("www.settest.com");
        shops.add(s);

        s.set_id(2);
        s.setMnombre("Nombre 2");
        s.setMprecio(11);
        s.setMrating(3);
        s.setMservicio(7);
        s.setTelefono("123456789");
        s.setWeb("www.settest.com");
        shops.add(s);

        s.set_id(3);
        s.setMnombre("Nombre 3");
        s.setMprecio(13);
        s.setMrating(5);
        s.setMservicio(7);
        s.setTelefono("123456789");
        s.setWeb("www.settest.com");
        shops.add(s);

        s.set_id(4);
        s.setMnombre("Nombre 4");
        s.setMprecio(2);
        s.setMrating(4);
        s.setMservicio(7);
        s.setTelefono("123456789");
        s.setWeb("www.settest.com");
        shops.add(s);

        s.set_id(5);
        s.setMnombre("Nombre 5");
        s.setMprecio(56);
        s.setMrating(2);
        s.setMservicio(7);
        s.setTelefono("123456789");
        s.setWeb("www.settest.com");
        shops.add(s);
        return
    }

    @Override
    public Shop getTienda(long id) {
        for(Shop s:shops){
            if (s.get_id() == id) return s;
        }
        return null;
    }

    @Override
    public void removeShop(long id) {
        for (Shop s:shops){
            if (s.get_id() == id) shops.remove(s)
        }
    }

    @Override
    public void saveShop(long id) {
        for(Shop s:shops){
            if (s.get_id() == 0){
                s.set_id(getNextId());
                shops.add(s);
            }
        }
    }

    private long lastId = OL;
    private synchronized long getNextId(){
        return ++lastId;
    }
}

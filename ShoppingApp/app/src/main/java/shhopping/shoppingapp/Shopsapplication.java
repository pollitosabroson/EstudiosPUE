package shhopping.shoppingapp;

import android.app.Application;

import shhopping.shoppingapp.service.InMemoryShoService;
import shhopping.shoppingapp.service.Shopservice;

/**
 * Created by daa on 06/02/2017.
 */

public class Shopsapplication extends Application {

    private Shopservice shopservice;
    private static Shopsapplication minstance;
    @Override
    public void onCreate() {
        super.onCreate();
        shopservice = new InMemoryShoService();
    }

    public static Shopsapplication getInstance(){
        return minstance;
    }
    public Shopservice getShopService(){
        return shopservice;
    }
}

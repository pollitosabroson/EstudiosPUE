package es.pue.sergi.shapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListTiendasFragment.OnListItemFragmentListener {

    Animation remarkButtonAnimation;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        remarkButtonAnimation = AnimationUtils.loadAnimation(this,R.anim.beat);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remarkButton();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add the list fragment to this activity
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.list_fragment,new ListTiendasFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id== R.id.backup_settings){
            Intent explicito=new Intent(this,BackupActivity.class);
            startActivity(explicito);
            /*
            PopupMenu menu=new PopupMenu(this,findViewById(R.id.app_bar_layout));
            menu.getMenuInflater().inflate(R.menu.backup_menu,menu.getMenu());
            //TODO marcar ya las opciones que estan checkeadas
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    boolean result=false;
                    switch(item.getItemId()){
                        case R.id.backup_menu_sd:
                            if(item.isChecked()){
                                item.setChecked(false);
                            }else{
                                item.setChecked(true);
                            }
                            //TODO guardar la preferencia de usuario
                            result=true;
                            break;
                        case R.id.backup_menu_cloud:
                            if(item.isChecked()){
                                item.setChecked(false);
                            }else{
                                item.setChecked(true);
                            }
                            //TODO guardar la preferencia de usuario
                            result=true;
                            break;
                    }
                    return result;
                }
            });
            menu.show();
            */
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onListItemClick(long tienda_id) {
        DetailTiendasFragment detailFragment=new DetailTiendasFragment();
        detailFragment.setTienda_id(tienda_id);
        if(findViewById(R.id.detail_fragment)==null){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction()
                    .replace(R.id.list_fragment,detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            FragmentManager fm=getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.detail_fragment,detailFragment)
                    .commit();
        }
    }

    private void remarkButton(){
        fab.startAnimation(remarkButtonAnimation);
    }
}

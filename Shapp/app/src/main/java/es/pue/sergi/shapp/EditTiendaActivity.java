package es.pue.sergi.shapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditTiendaActivity extends AppCompatActivity {

    public static final String TIENDA_ID_EXTRA_KEY="tienda_id";
    private long tienda_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tienda);

        tienda_id=getIntent().getLongExtra(TIENDA_ID_EXTRA_KEY,0);
        if(tienda_id==0){
            //TODO error
        }


    }
}

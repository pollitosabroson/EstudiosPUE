package es.pue.sergi.shapp;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import es.pue.sergi.shapp.model.Tienda;
import es.pue.sergi.shapp.views.ValorationBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTiendasFragment extends Fragment implements View.OnClickListener{
    private long tienda_id;
    private Button webButton;
    private Button callButton;
    private Tienda current;
    ValorationBar precio;
    ValorationBar servicio;

    public DetailTiendasFragment(){}

    public void setTienda_id(long tienda_id){
        this.tienda_id=tienda_id;
    }

    @Override
    public void onResume() {
        super.onResume();
        ObjectAnimator animatorPrice = ObjectAnimator.ofFloat(precio,"valoracion",0.0f,current.getPrecio());
        ObjectAnimator animatorService = ObjectAnimator.ofFloat(servicio,"valoracion",0.0f,current.getServicio());
        animatorPrice.setDuration(5000);
        animatorService.setDuration(5000);
        animatorPrice.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorService.setInterpolator(new AccelerateInterpolator());
        animatorPrice.start();
        animatorService.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_detail, container, false);

        Log.d("DetailFragment","Tienda id is "+tienda_id);

        current=TiendasApplication.getInstance().getTiendasService().getTienda(tienda_id);

        TextView nombre=(TextView)v.findViewById(R.id.nombre_tienda_detail);
        precio=(ValorationBar)v.findViewById(R.id.precio_tienda_detail);
        servicio=(ValorationBar)v.findViewById(R.id.servicio_tienda_detail);
        RatingBar valoracion=(RatingBar)v.findViewById(R.id.valoracion_tienda_detail);

        nombre.setText(current.getNombre());

        precio.setValoracion(current.getPrecio());

        servicio.setValoracion(current.getServicio());
        valoracion.setRating(current.getRating());

        webButton=(Button) v.findViewById(R.id.web_button);
        callButton=(Button) v.findViewById(R.id.call_button);
        webButton.setOnClickListener(this);
        callButton.setOnClickListener(this);
        ((Button)v.findViewById(R.id.edit_button)).setOnClickListener(this);
        webButton.setText(getString(R.string.detail_web_button));
        if(current.getTelefono()!=null && !current.getTelefono().isEmpty()){
            callButton.setVisibility(View.VISIBLE);
        }else{
            callButton.setVisibility(View.INVISIBLE);
        }
        if(current.getWeb()!=null && !current.getWeb().isEmpty()){
            webButton.setVisibility(View.VISIBLE);
        }else{
            webButton.setVisibility(View.INVISIBLE);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_button:
                Intent call= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+current.getTelefono()));
                startActivity(call);
                break;
            case R.id.web_button:
                Intent web= new Intent(Intent.ACTION_VIEW, Uri.parse(current.getWeb()));
                startActivity(web);
                break;
            case R.id.edit_button:
                Intent edit=new Intent(getActivity(),EditTiendaActivity.class);
                edit.putExtra(EditTiendaActivity.TIENDA_ID_EXTRA_KEY,tienda_id);
                startActivity(edit);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("DetailFragment","On save");
        outState.putLong("a",tienda_id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i("DetailFragment","On restore");
        if(savedInstanceState!=null)
            tienda_id=savedInstanceState.getLong("a");
        super.onViewStateRestored(savedInstanceState);
    }
}

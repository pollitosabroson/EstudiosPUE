package es.pue.sergi.shapp;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.pue.sergi.shapp.model.Tienda;
import es.pue.sergi.shapp.service.TiendasService;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListTiendasFragment extends Fragment {

    ListView list;
    private OnListItemFragmentListener mListener;


    public ListTiendasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnListItemFragmentListener){
            mListener=(OnListItemFragmentListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list_tiendas, container, false);

        list=(ListView) v.findViewById(R.id.fragment_list_view);

        final TiendasListAdapter adapter=new TiendasListAdapter();

        //asociamos el adapter a la listview
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ListFragment","Clicked on tienda with id "+id);
                mListener.onListItemClick(id);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(getActivity());
                alertBuilder.setTitle(getString(R.string.list_fragment_delete_alert_title));
                alertBuilder.setMessage(getString(R.string.list_fragment_delete_alert_message));
                alertBuilder.setNegativeButton(R.string.list_fragment_delete_alert_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertBuilder.setPositiveButton(R.string.list_fragment_delete_alert_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TiendasApplication.getInstance().getTiendasService().removeTienda(id);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(),R.string.list_fragment_delete_ok_message,Toast.LENGTH_LONG).show();
                        //dialog.cancel();
                    }
                });
                alertBuilder.show();
                return true;
            }
        });

        return v;
    }

    public class TiendasListAdapter extends BaseAdapter{

        List<Tienda> tiendas;

        public TiendasListAdapter(){
            tiendas=TiendasApplication.getInstance().getTiendasService().getAllTiendas();
        }

        @Override
        public int getCount() {
            return tiendas.size();
        }

        @Override
        public Object getItem(int position) {
            return tiendas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return tiendas.get(position).get_id();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                LayoutInflater inflater=LayoutInflater.from(getContext());
                convertView=inflater.inflate(R.layout.fragment_list_tiendas_item,parent,false);
            }
            TextView nombre=(TextView)convertView.findViewById(R.id.nombre_tienda);
            TextView precio=(TextView)convertView.findViewById(R.id.precio_tienda);
            TextView servicio=(TextView)convertView.findViewById(R.id.servicio_tienda);
            RatingBar valoracion=(RatingBar)convertView.findViewById(R.id.valoracion_tienda);

            Tienda current=tiendas.get(position);

            nombre.setText(current.getNombre());
            precio.setText(String.valueOf(current.getPrecio()));
            servicio.setText(String.valueOf(current.getServicio()));
            valoracion.setRating(current.getRating());

            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            tiendas=TiendasApplication.getInstance().getTiendasService().getAllTiendas();
            super.notifyDataSetChanged();
        }

    }

    public interface OnListItemFragmentListener{
        void onListItemClick(long tienda_id);
    }



}

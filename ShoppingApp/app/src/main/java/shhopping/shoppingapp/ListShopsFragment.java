package shhopping.shoppingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import shhopping.shoppingapp.model.Shop;


public class ListShopsFragment extends Fragment {
    ListView list;
    private OnlistenerFragmentClick mlistener;

    public ListShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_shops, container, false);
        list = (ListView) v.findViewById(R.id.fragment_list);

        ShopsListAdapter adapter = new ShopsListAdapter();

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return v;
    }

    public class ShopsListAdapter extends BaseAdapter{

        List<Shop> shops;
        public ShopsListAdapter(){
            shops = Shopsapplication.getInstance().getShopService().getAlltiendas();
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return shops.get(position);
        }

        @Override
        public long getItemId(int position) {
            return shops.get(position).get_id();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.fragment_list_shop_item, parent, false);
            }
            TextView name = (TextView) convertView.findViewById(R.id.name_shop);
            TextView price = (TextView) convertView.findViewById(R.id.price_shop);
            TextView service = (TextView) convertView.findViewById(R.id.service_shop);
            RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratingBar);

            Shop current = shops.get(position);

            name.setText(current.getMnombre());
            price.setText(current.getMprecio());
            service.setText(current.getMservicio());
            rating.setRating(current.getMrating());
            return convertView;
        }
    }

    public interface OnlistenerFragmentClick{
        void onListItemClick(long shop_is);
    }
}

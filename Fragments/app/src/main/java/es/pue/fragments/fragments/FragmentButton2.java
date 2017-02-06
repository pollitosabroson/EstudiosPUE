package es.pue.fragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentButton2 extends Fragment {

    private FragmentButton2.OnfragmentInteractionListener2 mListener;
    public FragmentButton2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_button2, container, false);
        Button button1 = (Button)v.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("FRAGMNET2", "BUTTON 1 CLICKED");
                mListener.onButton2Cliked();
            }
        });
        return v;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentButton2.OnfragmentInteractionListener2) {
            mListener = (FragmentButton2.OnfragmentInteractionListener2) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnfragmentInteractionListener2 {
        // TODO: Update argument type and name
        void onButton2Cliked();
    }

}

package es.pue.fragments.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements FragmentButton1.OnfragmentInteractionListener1, FragmentButton3.OnFragmentInteractionListener3, FragmentButton2.OnfragmentInteractionListener2 {

    FrameLayout main;
    FrameLayout extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = (FrameLayout)findViewById(R.id.main_fragment);
        extra =  (FrameLayout)findViewById(R.id.extra_fragment);

        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ftrans = fm.beginTransaction();

        ftrans.add(R.id.main_fragment, new FragmentButton1());
        if (extra !=null){
            ftrans.add(R.id.extra_fragment, new FragmentButton2());
        }
        ftrans.commit();
    }

    @Override
    public void onButton1Cliked() {
        Log.i("Activity", "click in the button1 into main activity");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftrans =  fm.beginTransaction();
        if (extra != null){
            ftrans.replace(R.id.extra_fragment, new FragmentButton3());
        }else{
            ftrans.replace(R.id.main_fragment, new FragmentButton2());
        }
        ftrans.commit();
    }

    @Override
    public void onButton3Cliked() {
        Log.i("Activity", "click in the button1 into main activity");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftrans =  fm.beginTransaction();
        if (extra != null){
            ftrans.replace(R.id.extra_fragment, new FragmentButton3());
        }else{
            ftrans.replace(R.id.main_fragment, new FragmentButton2());
        }
        ftrans.commit();
    }

    @Override
    public void onButton2Cliked() {
        Log.i("Activity", "click in the button1 into main activity");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ftrans =  fm.beginTransaction();
        if (extra != null){
            ftrans.replace(R.id.extra_fragment, new FragmentButton2());
        }else{
            ftrans.replace(R.id.main_fragment, new FragmentButton1());
        }
        ftrans.commit();
    }
}

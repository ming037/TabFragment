package com.example.hwi.fragmentproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.placeHolder);

        if(savedFragment == null)
        {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        //여기에 쓰는 것이 List_Fragment에서 일어나는 일이다.
        //UI의 변화는 Activity가 관리하기 때문에 이렇게 하는것.
        Toast.makeText(this, Recipes.names[index], Toast.LENGTH_SHORT).show();
    }
}

package com.example.hwi.fragmentproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface{
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT= "viewpager_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment savedFragment = (ListFragment) getFragmentManager().
                findFragmentByTag(LIST_FRAGMENT);

        if(savedFragment == null)
        {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        //여기에 쓰는 것이 List_Fragment에서 일어나는 일이다.
        //UI의 변화는 Activity가 관리하기 때문에 이렇게 하는것.
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

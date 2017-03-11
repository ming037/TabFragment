package com.example.hwi.fragmentproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by hwi on 17. 3. 11.
 */

public class ViewPagerFragment extends Fragment{

   public static final String KEY_RECIPE_INDEX = "recipe_index";

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        //Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);

        final IngredientsFragment ingredientFragment = new IngredientsFragment();
        final DirectionsFragment directionsFragment = new DirectionsFragment();


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position==0?  ingredientFragment :directionsFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position==0? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}

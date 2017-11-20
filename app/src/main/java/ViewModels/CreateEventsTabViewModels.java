package ViewModels;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class CreateEventsTabViewModels extends  Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    View myView;

    FragmentManager fgk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.eventdisplaytabs, container, false);
        fgk = this.getActivity().getSupportFragmentManager();

        viewPager = (ViewPager) myView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) myView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return myView;

    }
    private void setupViewPager(ViewPager viewPager) {
        CreateEventsTabViewModels.ViewPagerAdapter adapter = new CreateEventsTabViewModels.ViewPagerAdapter(fgk);
        adapter.addFragment(new GrafikViewModels(),"Kalendarz");
        adapter.addFragment(new MyEventDisplay(),"Moje Zajecia");


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

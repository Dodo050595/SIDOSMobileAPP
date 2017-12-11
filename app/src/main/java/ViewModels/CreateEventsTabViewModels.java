package ViewModels;


import android.content.Context;
import android.os.AsyncTask;
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
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Adapters.EventAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Event;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class CreateEventsTabViewModels extends  Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    View myView;
    Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    public List<Event> EventList = new ArrayList<Event>();

    FragmentManager fgk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.eventdisplaytabs, container, false);
        fgk = getChildFragmentManager();

        try {
            String str_result = new CreateEventsTabViewModels.AsyncGetEvents(getContext()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        viewPager = (ViewPager) myView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) myView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return myView;

    }
    private void setupViewPager(ViewPager viewPager) {
        CreateEventsTabViewModels.ViewPagerAdapter adapter = new CreateEventsTabViewModels.ViewPagerAdapter(fgk);
        String openString = gSon.toJson(EventList);
        Fragment EventList = GrafikViewModels.newInstance(openString);
        adapter.addFragment(EventList,"Kalendarz");
        Fragment EventList2 = MyEventDisplay.newInstance(openString);
        adapter.addFragment(EventList2,"Moje Zajecia");


        viewPager.setAdapter(adapter);
    }

 private class ViewPagerAdapter extends FragmentPagerAdapter {
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
    public class AsyncGetEvents extends AsyncTask<Void,Void,String> {

        private Context cont;
        String message = "";
        Boolean error = false;

        public AsyncGetEvents(Context _cont){
            cont = _cont;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            if(error){
                HelperMethods.CreateErrorAlert(getActivity(),"Błąd",message);
            }

            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... params) {
            Type listType = new TypeToken<ArrayList<Event>>(){}.getType();

            try {

                // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));
                Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                EventList = gSon.fromJson(HelperMethods.sendGet(Utils.EventAPI), listType);


            } catch (Exception e) {
                error = true;
                message = e.getMessage();
            }

            return "Done";
        }
    }
}

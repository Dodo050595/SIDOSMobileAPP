package ViewModels;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Adapters.TasksAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-27.
 */

public class TaskDisplayOnTab extends Fragment {

    ProgressDialog progressDialog;
    List<Task> OpenTasks;
    List<Task> RejectedTasks;
    List<Task> AcceptedTasks;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View myView;
    Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    FragmentManager fgk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.taskdisplaytabs, container, false);
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
                OpenTasks     = new ArrayList<Task>();
                RejectedTasks = new ArrayList<Task>();
                AcceptedTasks = new ArrayList<Task>();
        fgk = getChildFragmentManager();
        try {
            String str_result = new AsyncgetTasksForUser(getContext()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        viewPager = (ViewPager) myView.findViewById(R.id.viewpagerTask);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) myView.findViewById(R.id.tabsTask);
        tabLayout.setupWithViewPager(viewPager);
        progressDialog.dismiss();
        return myView;


    }
    private void setupViewPager(ViewPager viewPager) {
        TaskDisplayOnTab.ViewPagerAdapter adapter = new TaskDisplayOnTab.ViewPagerAdapter(fgk);
        String openString = gSon.toJson(OpenTasks);
        Fragment OpenTasks = TaskDisplayViewModel.newInstance(openString);
        adapter.addFragment(OpenTasks,"Otwarte zadania");
        String acceptedString = gSon.toJson(AcceptedTasks);
        Fragment AcceptedTasks = TaskDisplayAcceptedViewModels.newInstance(acceptedString);
        adapter.addFragment(AcceptedTasks,"Zaakceptowane zadania");
        String rejectedString = gSon.toJson(RejectedTasks);
        Fragment RejectedTasks = TaskDisplayRejectedViewModels.newInstance(rejectedString);
        adapter.addFragment(RejectedTasks,"Odrzucone zadania");

        viewPager.setAdapter(adapter);
    }

 private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

             @Override
             public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
                 return POSITION_NONE;
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
        class AsyncgetTasksForUser extends AsyncTask<Void, Void, String> {

            private Context cont;
            public List<Task> tsk = new ArrayList<Task>();

            public AsyncgetTasksForUser(Context cont) {
                this.cont = cont;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(Void... params) {
                Type listType = new TypeToken<ArrayList<Task>>() {
                }.getType();

                try {

                    // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));
                    Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    tsk = gSon.fromJson(HelperMethods.sendGet(Utils.TaskAPI), listType);

                    for(Task ts : tsk){
                        if(ts.getStatus().equalsIgnoreCase("Planned") || ts.getStatus().equalsIgnoreCase("Changed") ){
                            OpenTasks.add(ts);
                        }
                        if(ts.getStatus().equalsIgnoreCase("Denied")){
                            RejectedTasks.add(ts);
                        }
                        if(ts.getStatus().equalsIgnoreCase("Accepted")){
                            AcceptedTasks.add(ts);
                        }
                    }

                } catch (Exception e) {
                    HelperMethods.CreateErrorAlert(getActivity(),"Błąd",e.getMessage());
                }

                return null;
            }
        }
    }

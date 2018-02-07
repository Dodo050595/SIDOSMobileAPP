package ViewModels;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Adapters.EmployeeAdapter;
import Adapters.EventAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Event;
import Models.Pracownik;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.DisplayEmployeeActivity;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class MyEventDisplay extends Fragment {

    View myView;
    ListAdapter adapter;
    ListView listv;
    ProgressDialog progressDialog;

    public static Fragment newInstance(String tsk) {
        MyEventDisplay myFragment = new MyEventDisplay();

        Bundle args = new Bundle();
        args.putSerializable("Event", tsk);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.myeventsdisplay, container, false);
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Ładowanie. Proszę czekać...", true);
        String a =  (String) getArguments().getSerializable("Event");
        List<Event> EventList;
        Type listType = new TypeToken<ArrayList<Event>>() {
        }.getType();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        EventList = gSon.fromJson(a, listType);
        SortCurrentDate(EventList);
            adapter = new EventAdapter(myView.getContext(), (ArrayList<Event>) EventList);
            listv = (ListView) myView.findViewById(R.id.ListViewMyEvent);
            listv.setAdapter(adapter);
        progressDialog.dismiss();


        return myView;
    }

    private void SortCurrentDate(List<Event> events){

        for (Iterator<Event> it = events.iterator(); it.hasNext(); ) {
            Event ev = it.next();
            if(HelperMethods.getStringFromDate(ev.getDateStart()).equals(HelperMethods.getCurrentDateString())){
                events.set(0,ev);

            }
        }
    }


}

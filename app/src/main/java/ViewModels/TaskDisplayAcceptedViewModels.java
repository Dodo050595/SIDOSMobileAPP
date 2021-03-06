package ViewModels;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import Adapters.TasksAdapter;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-27.
 */

public class TaskDisplayAcceptedViewModels extends Fragment {

    public View myView;
    ListAdapter adapter;
    ListView listv;


    public static Fragment newInstance(String tsk) {
        TaskDisplayAcceptedViewModels myFragment = new TaskDisplayAcceptedViewModels();

        Bundle args = new Bundle();
        args.putSerializable("Event", tsk);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.taskdisplay, container, false);
        Bundle args = getArguments();
        String a =  (String) args.getSerializable("Event");
        List<Task> tsk = new ArrayList<Task>();
        Type listType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        tsk = gSon.fromJson(a, listType);
        if(tsk != null) {
            adapter = new TasksAdapter(myView.getContext(), (ArrayList<Task>) tsk);
            listv = (ListView) myView.findViewById(R.id.ListViewTasks);
            listv.setAdapter(adapter);
        }

        return myView;
    }
}

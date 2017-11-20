package ViewModels;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class TaskDisplayViewModel extends Fragment {
    ProgressDialog progressDialog;
    public View myView;
    ListAdapter adapter;
    ListView listv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        myView = inflater.inflate(R.layout.taskdisplay, container, false);


        new AsyncgetTasksForUser(getContext()).execute();

        return myView;
    }
    class AsyncgetTasksForUser extends AsyncTask<Void,Void,String> {

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
        protected void onPostExecute(String s) {
            adapter = new TasksAdapter(myView.getContext(),(ArrayList<Task>) tsk);
            listv = (ListView) myView.findViewById(R.id.ListViewTasks);
            listv.setAdapter(adapter);

//            listv.setOnItemClickListener(
//                    new AdapterView.OnItemClickListener(){
//
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                            Intent intent = new Intent(view.getContext(), DisplayEmployeeActivity.class);
//                            Pracownik prc = (Pracownik) parent.getItemAtPosition(position);
//                            intent.putExtra(Utils.employeeSer, prc);
//                            startActivity(intent);
//                        }
//                    });

            progressDialog.dismiss();
            super.onPostExecute(s);

        }

        @Override
        protected String doInBackground(Void... params) {
            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();

            try {

                // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));
                Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                tsk = gSon.fromJson(HelperMethods.sendGet(Utils.TaskAPI), listType);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}


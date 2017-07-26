package ViewModels;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Adapters.EmployeeAdapter;
import Adapters.VetRequestAdapter;
import HelperClasses.HelperMethods;
import Models.Kon;
import Models.Pracownik;
import Models.VetRequest;
import pl.edu.s12898pjwstk.sidosmobile.DisplayHorseActivity;
import pl.edu.s12898pjwstk.sidosmobile.R;
import pl.edu.s12898pjwstk.sidosmobile.addnewRquest;

/**
 * Created by Dominik Deja on 22.05.2017.
 */

public class VetRequestViewModels extends Fragment {
    View myView;
    ListAdapter adapter;
    ListView listv;
    ProgressDialog progressDialog;
    public final String getVetRequest = "http://sidosapitest120170507071308.azurewebsites.net/api/healthreportissues";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);

        myView = inflater.inflate(R.layout.vetrequest, container, false);

        final Button btn = (Button) myView.findViewById(R.id.AddVetRequest);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Toast toast = Toast.makeText(v.getContext(), "Test toast", Toast.LENGTH_LONG);
//                toast.show();
                Intent intent = new Intent(myView.getContext(), addnewRquest.class);
                startActivity(intent);
            }
        });

        new AsyncGetVetRequest(myView.getContext()).execute();

        return myView;
    }
    public class AsyncGetVetRequest extends AsyncTask<Void,Void,String> {

        private Context cont;
        public List<VetRequest> vetRequests;

        public AsyncGetVetRequest(Context _cont){
            cont = _cont;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter = new VetRequestAdapter(myView.getContext(),(ArrayList<VetRequest>) vetRequests);
            listv = (ListView) myView.findViewById(R.id.VetRequestList);
            listv.setAdapter(adapter);

            listv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
            progressDialog.dismiss();
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... params) {
            Type listType = new TypeToken<ArrayList<VetRequest>>(){}.getType();

            try {

                Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                vetRequests = gSon.fromJson(HelperMethods.sendGet(getVetRequest), listType);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Done";
        }
    }
}

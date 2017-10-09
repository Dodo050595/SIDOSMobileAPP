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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Adapters.EmployeeAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Models.Pracownik;
import Adapters.HorseAdapter;
import pl.edu.s12898pjwstk.sidosmobile.DisplayEmployeeActivity;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class PracownikViewModels extends Fragment{
    View myView;
    ListAdapter adapter;
    ListView listv;
    ProgressDialog progressDialog;
    public final String getEmployee = "http://dev-sidos.azurewebsites.net/api/employees";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        myView = inflater.inflate(R.layout.pracownicy,container,false);
        new AsyncGetEmployee(myView.getContext()).execute();

        return myView;
    }
    public class AsyncGetEmployee extends AsyncTask<Void,Void,String> {

        private Context cont;
        public List<Pracownik> pracownicy;

        public AsyncGetEmployee(Context _cont){
            cont = _cont;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter = new EmployeeAdapter(myView.getContext(),(ArrayList<Pracownik>) pracownicy);
            listv = (ListView) myView.findViewById(R.id.EmployeeList);
            listv.setAdapter(adapter);

            listv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            Intent intent = new Intent(view.getContext(), DisplayEmployeeActivity.class);
                            Pracownik prc = (Pracownik) parent.getItemAtPosition(position);
                            intent.putExtra(Utils.employeeSer, prc);
                            startActivity(intent);
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
            Type listType = new TypeToken<ArrayList<Pracownik>>(){}.getType();

            try {

                // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));
                Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                pracownicy = gSon.fromJson(HelperMethods.sendGet(getEmployee), listType);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Done";
        }
    }
}

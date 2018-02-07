package ViewModels;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.lang.reflect.Type;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Adapters.HorseAdapter;
import pl.edu.s12898pjwstk.sidosmobile.DisplayHorseActivity;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class KonieViewModels extends Fragment {

    View myView;
    ListView listv;
    ListView ls;
    ListAdapter adapter;
    Spinner spin;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        progressDialog= ProgressDialog.show(getActivity(), "",
                "Ładowanie. Proszę czekać...", true);
        myView = inflater.inflate(R.layout.konie, container, false);
        ls = (ListView) myView.findViewById(R.id.HorseLIst);
        spin = (Spinner) myView.findViewById(R.id.spinner_horse);
        SearchView searchView = (SearchView) myView.findViewById(R.id.SearchViewHorse);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2) {
                    new AsyncGetHorse(myView.getContext(), ls, spin, query, spin.getSelectedItem().toString()).execute();
                }else if(query.length() > 0 && query.length() < 3){
                    Toast.makeText(getActivity(), "Prosze wpisać dłuższy ciąg znaków !!",
                            Toast.LENGTH_LONG).show();
                }
                if(query.length() == 0){
                    new AsyncGetHorse(myView.getContext(),ls,spin).execute();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() == 0){
                    new AsyncGetHorse(myView.getContext(),ls,spin).execute();
                }
                return true;
            }
            });

        new AsyncGetHorse(myView.getContext(),ls,spin).execute();

//        ArrayList<Kon> konie = new ArrayList<Kon>();
//        Kon kn1 = new Kon("Pimpek","Italian",HelperMethods.getDateString("1995-05-06"));
//        Kon kn2 = new Kon("Diament","Chaotic",HelperMethods.getDateString("1998-08-11"));
//        Kon kn3 = new Kon("Rico","Brown",HelperMethods.getDateString("2000-02-02"));
//
//        konie.add(kn1);
//        konie.add(kn2);
//        konie.add(kn3);
//
//        ListAdapter adapter = new HorseAdapter(myView.getContext(), konie);
//        ListView listv = (ListView) myView.findViewById(R.id.HorseLIst);
//        listv.setAdapter(adapter);

        return myView;

    }

    public class AsyncGetHorse extends AsyncTask<Void,Void,String> {
        ListAdapter adapter;
        Context cont;
        ListView ls;
        Spinner spin;
        String QueryText;
        ArrayAdapter<CharSequence> Horseadapter;
        String spinnerValue;
        String message = "";
        Boolean error = false;

        @Override
        protected void onProgressUpdate(Void... values) {
            listv.setAdapter(adapter);
        }

        public AsyncGetHorse(Context con, ListView lst,Spinner _spin,String _QueryText,String _spinnerValue){
           cont = con;
            ls = lst;
            spin = _spin;
            QueryText = _QueryText;
            spinnerValue = _spinnerValue;
        }
        public AsyncGetHorse(Context con, ListView lst,Spinner _spin){
            cont = con;
            ls = lst;
            spin = _spin;
        }
        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String s) {
            if(error){
                HelperMethods.CreateErrorAlert(getActivity(),"Błąd",message);
            }else {
                listv.setAdapter(adapter);
                listv.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(view.getContext(), DisplayHorseActivity.class);
                                Kon kn = (Kon) parent.getItemAtPosition(position);
                                intent.putExtra(Utils.konSer, kn);
                                startActivity(intent);
                            }
                        });
                if (spin.getAdapter() == null) {
                    spin.setAdapter(Horseadapter);
                }

            }

        progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(Void... params) {
            Type listType = new TypeToken<ArrayList<Kon>>(){}.getType();

            if(spinnerValue == null) {
                try {

                   // JSONArray arrayHorseList = new JSONArray(HelperMethods.sendGet(URLHorseWebServie));
                    Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    List<Kon> konie = new ArrayList<Kon>();
                    konie = gSon.fromJson(HelperMethods.sendGet(Utils.HorseAPI), listType);

                    Horseadapter = ArrayAdapter.createFromResource(cont, R.array.planets_array, android.R.layout.simple_spinner_item);
                    Horseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter = new HorseAdapter(cont, (ArrayList<Kon>) konie);
                    listv = ls;


                } catch (Exception e) {
                    error=true;
                    message = e.getMessage();
                }
            }
            else if(spinnerValue.equals("Imie")){
                try {
                    Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    List<Kon> konie = gSon.fromJson(HelperMethods.sendGet(Utils.HorseAPIGetByName + QueryText), listType);
                    adapter = new HorseAdapter(cont, (ArrayList<Kon>) konie);
                    listv = ls;
                } catch (Exception e) {

                }
            }else if(spinnerValue.equals("Charakter")){
                try{
                    Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    List<Kon> konie = gSon.fromJson(HelperMethods.sendGet(Utils.HorseAPIGetByCharacter + QueryText), listType);
                    adapter = new HorseAdapter(cont,(ArrayList<Kon>) konie);
                    listv = ls;
                } catch (Exception e) {

                }
            }
            return "done";
        }

    }
}

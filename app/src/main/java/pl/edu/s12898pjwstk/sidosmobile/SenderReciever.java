package pl.edu.s12898pjwstk.sidosmobile;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.widget.ThemedSpinnerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Event;
import Models.Task;
import ViewModels.TaskDisplayOnTab;

/**
 * Created by dejad on 2018-01-14.
 */

public class SenderReciever extends WakefulBroadcastReceiver {

    private List<Event> event;
    private int NumberCount = 0;

    @Override
    public void onReceive(Context context, Intent intent) {


        try {
            String str_result = new AsyncGetCountOfEvent(context).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(NumberCount > 0){
                HelperMethods.sendNotification(context,"Masz dzisiaj: " + NumberCount + " wydarzeń. Koniecznie je sprawdź !!");
        }
    }


    public class AsyncGetCountOfEvent extends AsyncTask<Void,Void,String> {

       public AsyncGetCountOfEvent(Context cont){

       }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Type listType = new TypeToken<ArrayList<Event>>() {
            }.getType();

            try {
                Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                event = gSon.fromJson(HelperMethods.sendGet(Utils.EventApigetByDate+HelperMethods.getStringFromDateInEventFormat(HelperMethods.getCurrentDate())), listType);
                NumberCount = event.size();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

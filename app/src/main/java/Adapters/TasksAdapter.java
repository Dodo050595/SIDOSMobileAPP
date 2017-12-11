package Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Models.Task;
import Models.TaskChangeStatusDto;
import ViewModels.TaskDisplayOnTab;
import pl.edu.s12898pjwstk.sidosmobile.MainBar;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class TasksAdapter extends ArrayAdapter<Task> {
    public static ProgressDialog progressDialog;
    Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    Context cont;
    public TasksAdapter(@NonNull Context context, ArrayList<Task> tsk) {
        super(context, R.layout.custom_row,tsk);
        cont = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        final View customView = buckysInflater.inflate(R.layout.customrowwithoutphoto,parent,false);
        final Activity act = (MainBar) parent.getContext();
        String horseNames = "Kon: ";

        final Task tsk = getItem(position);
        Button accBTN = (Button) customView.findViewById(R.id.Accept_Button_Task);
        Button rejBTN = (Button) customView.findViewById(R.id.Reject_Button_Task);
        TextView firstValue = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView secondValue = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView thirdValue  = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        TextView fourthValue  = (TextView) customView.findViewById(R.id.Row_FourthValue);
        TextView FifthValue  = (TextView) customView.findViewById(R.id.Row_FifthValueComment);

        TextView title  = (TextView) customView.findViewById(R.id.TaskNumber);

        firstValue.setText("Typ: " + tsk.getType());
        for(Kon kn : tsk.getRefersTo()){
            horseNames += kn.getName();
        }
        secondValue.setText(horseNames);
        thirdValue.setText("Instruktor: " + ((tsk.getAssignedTo() == null) ? "" : tsk.getAssignedTo().toString()));
        fourthValue.setText("Dawka: " + tsk.getDosage());
        FifthValue.setText("Opis: " + tsk.getDescription());
        title.setText("Zadanie");

        if(tsk.getStatus().equalsIgnoreCase("Planned") || tsk.getStatus().equalsIgnoreCase("Changed") ){
            accBTN.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    progressDialog = ProgressDialog.show(getContext(), "",
                            "Loading. Please wait...", true);
                    TaskChangeStatusDto tskChange = new TaskChangeStatusDto(tsk.getId(),"Accepted"
                    );
                    final String jsonTskAcc = gSon.toJson(tskChange);
                    try {
                                new AsyncChangeStatus(act,jsonTskAcc).execute();

                    } catch (Exception e) {
                    }
                }
            });
            rejBTN.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    progressDialog = ProgressDialog.show(getContext(), "",
                            "Loading. Please wait...", true);
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    final EditText edittext = new EditText(getContext());
                    alert.setMessage("Powod odrzucenia");
                    alert.setTitle("Decyzja Negatywna");
                    alert.setView(edittext);

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            progressDialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            String cancellationCauseStr = edittext.getText().toString();
                            TaskChangeStatusDto tskChange = new TaskChangeStatusDto(tsk.getId(),"Denied"
                            ,cancellationCauseStr);
                            final String jsonTskRej = gSon.toJson(tskChange);
                            try {

                                        new AsyncChangeStatus(act,jsonTskRej).execute();



                            } catch (Exception e) {

                            }
                        }
                    });


                    alert.show();



                }
            });
        }else {
            accBTN.setVisibility(View.GONE);
            rejBTN.setVisibility(View.GONE);
            if (tsk.getStatus().equalsIgnoreCase("Denied")) {
                fourthValue.setText("Powod odrzucenia: " + tsk.getCancellationCause());
                title.setText("Zadanie Odrzucone");
            }
            if (tsk.getStatus().equalsIgnoreCase("Accepted")) {
                title.setText("Zadanie Zaakceptowane");
            }
        }

        return customView;
    }
    public class AsyncChangeStatus extends AsyncTask<Void,Void,String> {
        private boolean err = false;
        private String body;
        private Activity Activ;

        public AsyncChangeStatus(Activity _act,String _body){
            Activ = _act;
            body = _body;
        }

        @Override
        protected void onPostExecute(String s) {
            if(!err)
                HelperMethods.CreateInfoAlert(Activ, "Success", "Udało się zmienić status zadania !!");
            else
                HelperMethods.CreateErrorAlert(Activ,"Error","Nie Udało się zmienić status zadania !!");
            progressDialog.dismiss();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                HelperMethods.sendPost(Utils.TaskAPIChangeStatus,body);

            } catch (Exception e) {
                err = true;
            }
            return "Done";
        }
    }
}

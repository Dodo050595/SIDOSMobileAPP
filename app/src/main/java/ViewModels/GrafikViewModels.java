package ViewModels;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapters.HorseAdapter;
import Adapters.hourslineAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Event;
import Models.Kon;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-08-20.
 */

public class GrafikViewModels extends Fragment{
    View myView;
    Date dt = HelperMethods.getCurrentDate();
    TextView dateTextView;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    public static Fragment newInstance(String tsk) {
        GrafikViewModels myFragment = new GrafikViewModels();

        Bundle args = new Bundle();
        args.putSerializable("Event", tsk);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.grafik, container, false);
        System.out.println("Arguments = " + getArguments());
        Bundle args = getArguments();
        String a =  (String) args.getSerializable("Event");
        List<Event> EventList;
        Type listType = new TypeToken<ArrayList<Event>>() {
        }.getType();
        Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        EventList = gSon.fromJson(a, listType);

         Button ArrLeftbtn = (Button) myView.findViewById(R.id.ArrowLeft);
         Button ArrRightbtn = (Button) myView.findViewById(R.id.ArrowRight);
         dateTextView = (TextView) myView.findViewById(R.id.dateTextViewGrafik);
         dateTextView.setText(HelperMethods.getStringFromDate(dt));

         ArrLeftbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dt = HelperMethods.AddDays(dt,-1);
                dateTextView.setText(HelperMethods.getStringFromDate(dt));
            }
        });
        ArrRightbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               dt =  HelperMethods.AddDays(dt,1);
                dateTextView.setText(HelperMethods.getStringFromDate(dt));
            }
        });

        ListView HoursList = (ListView) myView.findViewById(R.id.hrsListView);
        ListAdapter adapter = new hourslineAdapter(getContext(), (ArrayList<String>) Utils.generateHoursOfLessons());
        HoursList.setAdapter(adapter);



        EditText edittext= (EditText) myView.findViewById(R.id.dateTextViewSzukajGrafik);

       date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return myView;
    }
    private void updateLabel() {
        dt = myCalendar.getTime();
        dateTextView.setText(HelperMethods.getStringFromDate(myCalendar.getTime()));
    }
}

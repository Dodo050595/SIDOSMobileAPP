package ViewModels;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import HelperClasses.HelperMethods;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.grafik, container, false);

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

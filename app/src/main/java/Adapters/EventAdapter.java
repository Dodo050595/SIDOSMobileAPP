package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileReader;
import java.util.ArrayList;

import HelperClasses.HelperMethods;
import Models.Event;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-12-10.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(@NonNull Context context, ArrayList<Event> event) {
        super(context, R.layout.customrowwithoutphoto,event);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.customrowwithoutphoto, parent, false);


        final Event ev = getItem(position);
        Button accBTN = (Button) customView.findViewById(R.id.Accept_Button_Task);
        accBTN.setVisibility(View.GONE);
        Button rejBTN = (Button) customView.findViewById(R.id.Reject_Button_Task);
        rejBTN.setVisibility(View.GONE);
        TextView firstValue = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView secondValue = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView thirdValue  = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        TextView fourthValue  = (TextView) customView.findViewById(R.id.Row_FourthValue);
        TextView FifthValue  = (TextView) customView.findViewById(R.id.Row_FifthValueComment);
        TextView title  = (TextView) customView.findViewById(R.id.TaskNumber);
        title.setText("Zajęcia: " + ev.getEvent());
        firstValue.setText("Typ: " + ev.getEvent());
        FifthValue.setText("Opis: " + ev.getDescription());
        thirdValue.setText("Start: " + HelperMethods.getStringFromDate(ev.getDateStart()));
        fourthValue.setText("Koniec: " + HelperMethods.getStringFromDate(ev.getDateEnd()));


        if(ev.getUrl() != null) {
            final SpannableStringBuilder sb = new SpannableStringBuilder(ev.getUrl());
            sb.setSpan(new ForegroundColorSpan(Color.BLUE), 0, ev.getUrl().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            secondValue.setText(sb);
            secondValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(ev.getUrl()));
                    getContext().startActivity(i);
                }
            });

        }

        return customView;
    }
}

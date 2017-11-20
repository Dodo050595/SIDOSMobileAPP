package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-13.
 */

public class hourslineAdapter extends ArrayAdapter<String> {

    public hourslineAdapter(@NonNull Context context, ArrayList<String> urlOfPhoto) {
        super(context, R.layout.hoursline,urlOfPhoto);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.hoursline,parent,false);
        String text = getItem(position);

        Button btn1 = (Button) customView.findViewById(R.id.hourslinebtn);
        btn1.setText(text);

        return customView;
    }
}

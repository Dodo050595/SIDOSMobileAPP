package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import HelperClasses.HelperMethods;
import Models.Kon;
import Models.Pracownik;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class EmployeeAdapter extends ArrayAdapter<Pracownik>{


    public EmployeeAdapter(@NonNull Context context, ArrayList<Pracownik> Prac) {
        super(context, R.layout.custom_row,Prac);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row,parent,false);

        Pracownik pracownik = getItem(position);
        TextView nazwa = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView pseudonim = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView data_urodzenia = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        ImageView imgView = (ImageView) customView.findViewById(R.id.imgView);


        nazwa.setText("Imie: " + pracownik.getFirstName());
        pseudonim.setText("Nazwisko: " + pracownik.getLastName());
        data_urodzenia.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(pracownik.getBirthDate()));
        imgView.setImageResource(R.drawable.pracownik);

        return customView;
    }
}

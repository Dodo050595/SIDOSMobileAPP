package pl.edu.s12898pjwstk.sidosmobile;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import Adapters.EmployeeAdapter;
import HelperClasses.HelperMethods;
import Models.Kon;
import Models.Pracownik;

/**
 * Created by Dominik Deja on 14.05.2017.
 */

public class DisplayHorseFragDisplay extends Fragment{
    View myView;
    Kon kn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.horsedisplayfragment,container,false);
        Kon kon = (Kon) getActivity().getIntent().getSerializableExtra("Kon");
        TextView val1 = (TextView) myView.findViewById(R.id.horsedsp_val1);
        TextView val2 = (TextView) myView.findViewById(R.id.horsedsp_val2);
        TextView val3 = (TextView) myView.findViewById(R.id.horsedsp_val3);
        TextView val4 = (TextView) myView.findViewById(R.id.horsedsp_val4);

        val1.setText("Imie: " + kon.getName());
        val2.setText("Charakter: " + kon.getCharacter());
        val3.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(kon.getBirthdate()));
        val4.setText("Wysokosc: " + kon.getHeight());
        return myView;
    }
}

package ViewModels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Pracownik;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-09-23.
 */

public class DisplayEmployeeFragDisplay extends Fragment {
    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.employeedisplayfragment,container,false);
        Pracownik prc = (Pracownik) getActivity().getIntent().getSerializableExtra(Utils.employeeSer);
        TextView val1 = (TextView) myView.findViewById(R.id.horsedsp_val1);
        TextView val2 = (TextView) myView.findViewById(R.id.horsedsp_val2);
        TextView val3 = (TextView) myView.findViewById(R.id.horsedsp_val3);


        val1.setText("Imie: " + prc.getFirstName());
        val2.setText("Nazwisko: " + prc.getLastName());
        val3.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(prc.getBirthDate()));
        return myView;
    }
}

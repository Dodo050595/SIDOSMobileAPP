package ViewModels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        TextView val1 = (TextView) myView.findViewById(R.id.emp_val1);
        TextView val2 = (TextView) myView.findViewById(R.id.emp_val2);
        TextView val3 = (TextView) myView.findViewById(R.id.emp_val3);
        TextView val4 = (TextView) myView.findViewById(R.id.emp_val4);
        TextView val5 = (TextView) myView.findViewById(R.id.emp_val5);
        TextView val6 = (TextView) myView.findViewById(R.id.emp_val6);
        TextView val7 = (TextView) myView.findViewById(R.id.emp_val7);
        TextView val8 = (TextView) myView.findViewById(R.id.emp_val8);
        ImageView imgView = (ImageView) myView.findViewById(R.id.imageViewEmployee);

        val1.setText("Imie: " + prc.getFirstName());
        val2.setText("Nazwisko: " + prc.getLastName());
        val3.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(prc.getdateOfBirth()));
        val4.setText("Telefon: " + prc.getphoneNumber());
        val5.setText("Email: " + prc.getemailAddress());
        val6.setText("Stanowisko: " + prc.getEmployeeType());
        val7.setText("Data Zatrudnienia: " + HelperMethods.getStringFromDate(prc.getHireDate()));
        val8.setText("Login: " + prc.getUserName());

        if(prc.getProfilePicture() != null && prc.getProfilePicture() != "")
            Picasso.with(getContext()).load(Utils.URLFORAPI + prc.getProfilePicture()).into(imgView);
        else
            imgView.setImageResource(R.drawable.ic_menu_camera);

        return myView;
    }
}

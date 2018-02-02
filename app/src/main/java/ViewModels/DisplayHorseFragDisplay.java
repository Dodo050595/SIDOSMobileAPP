package ViewModels;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import Adapters.EmployeeAdapter;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Models.Pracownik;
import pl.edu.s12898pjwstk.sidosmobile.R;

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
            Kon kon = (Kon) getActivity().getIntent().getSerializableExtra(Utils.konSer);
            ImageView imgHRS = (ImageView) myView.findViewById(R.id.imageViewHorseAcc);
            TextView val1 = (TextView) myView.findViewById(R.id.horsedsp_val1);
            TextView val2 = (TextView) myView.findViewById(R.id.horsedsp_val2);
            TextView val3 = (TextView) myView.findViewById(R.id.horsedsp_val3);
            TextView val4 = (TextView) myView.findViewById(R.id.horsedsp_val4);
            TextView val5 = (TextView) myView.findViewById(R.id.horsedsp_val5);
            TextView val6 = (TextView) myView.findViewById(R.id.horsedsp_val6);
            TextView val7 = (TextView) myView.findViewById(R.id.horsedsp_val7);
            TextView val8 = (TextView) myView.findViewById(R.id.horsedsp_val8);
            TextView val9 = (TextView) myView.findViewById(R.id.horsedsp_val9);

            val1.setText("Imie: " + kon.getName());
            val2.setText("Charakter: " + kon.getCharacter());
            val3.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(kon.getDateOfBirth()));
            val4.setText("Wysokosc: " + kon.getHeight());
            val5.setText("Status: " + kon.getHealthStatus());
            val6.setText("Płeć: " +  kon.getSexType());
            val7.setText("Kolor: " +  kon.getCoatColour());
            val8.setText("W stadninie: " +  HelperMethods.getStringFromDate(kon.getSinceWhenInStable()));
            val9.setText("Komentarz: " +  kon.getComments());

                if(kon.getMainPicture() != null && kon.getMainPicture() != "" )
                    Picasso.with(getContext()).load(Utils.URLFORAPI + kon.getMainPicture()).into(imgHRS);
                else
                    imgHRS.setImageResource(R.drawable.ic_menu_camera);
            return myView;
        }
}

package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import HelperClasses.Utils;
import Models.Kon;
import ViewModels.DisplayHorseFragDisplay;
import pl.edu.s12898pjwstk.sidosmobile.R;

public class DisplayHorseActivity extends AppCompatActivity {

    android.app.FragmentManager fragmen = getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_horse);


        fragmen.beginTransaction().replace(
                R.id.disphorsemain,new DisplayHorseFragDisplay())
                .commit();
    }
    }

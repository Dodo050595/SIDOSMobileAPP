package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Models.Kon;
import ViewModels.KonieViewModels;

public class DisplayHorseActivity extends AppCompatActivity {

    android.app.FragmentManager fragmen = getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_horse);
        Kon model = (Kon) getIntent().getSerializableExtra(KonieViewModels.konSer);


        fragmen.beginTransaction().replace(
                R.id.disphorsemain,new DisplayHorseFragDisplay())
                .commit();
    }
    }

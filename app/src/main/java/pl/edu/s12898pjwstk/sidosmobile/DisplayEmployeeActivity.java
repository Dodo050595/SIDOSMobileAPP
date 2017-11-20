package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ViewModels.DisplayEmployeeFragDisplay;

public class DisplayEmployeeActivity extends AppCompatActivity {
    android.app.FragmentManager fragmen = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_employee);


        fragmen.beginTransaction().replace(
                R.id.dispemployeemain,new DisplayEmployeeFragDisplay())
                .commit();
    }
}

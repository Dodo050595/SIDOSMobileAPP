package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ViewModels.VetRequestDisplayViewModels;

public class vetrequestdisplay extends AppCompatActivity {

    android.app.FragmentManager fragmen = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vetrequestdisplay);

        fragmen.beginTransaction().replace(
                R.id.dispvetrequestmain,new VetRequestDisplayViewModels())
                .commit();
    }
}

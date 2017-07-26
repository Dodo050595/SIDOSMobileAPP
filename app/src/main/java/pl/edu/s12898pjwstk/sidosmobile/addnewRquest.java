package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import AddVetRequest.AddRequestFragment;
import ViewModels.KonieViewModels;

public class addnewRquest extends AppCompatActivity {

    android.app.FragmentManager fragmen = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_rquest);

        fragmen.beginTransaction().replace(
                R.id.disprequestfragment,new AddRequestFragment())
                .commit();
    }
}

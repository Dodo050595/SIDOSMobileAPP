package pl.edu.s12898pjwstk.sidosmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ViewModels.AddRequestFragment;
import pl.edu.s12898pjwstk.sidosmobile.R;

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

package pl.edu.s12898pjwstk.sidosmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class MainPageFragment extends Fragment{

    View myView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mainpage, container, false);

        return myView;
    }
}

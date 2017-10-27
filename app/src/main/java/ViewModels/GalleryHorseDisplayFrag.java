package ViewModels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.HorseGalleryAdapter;
import HelperClasses.Utils;
import Models.Kon;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-10-22.
 */

public class GalleryHorseDisplayFrag extends Fragment{

    View myView;
    Kon kn;
    ListAdapter adapter;
    ArrayAdapter<CharSequence> HorseGalleryadapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.horsedisplaygalleryfragment, container, false);
        Kon kon = (Kon) getActivity().getIntent().getSerializableExtra(Utils.konSer);

        ListView ls = (ListView) myView.findViewById(R.id.galleryListView);

        HorseGalleryadapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, android.R.layout.simple_spinner_item);
        HorseGalleryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter = new HorseGalleryAdapter(getContext(), (ArrayList<String>) kon.getPictureGalleryUrls());
        ls.setAdapter(adapter);


        return myView;
    }

}

package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Kon;
import Models.VetRequest;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 22.05.2017.
 */

public class VetRequestAdapter extends ArrayAdapter<VetRequest> {

    public VetRequestAdapter(@NonNull Context context, ArrayList<VetRequest> vetRequests) {
        super(context, R.layout.custom_row,vetRequests);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row,parent,false);

        VetRequest singleVetRequest = getItem(position);
        TextView nazwa = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView pseudonim = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView data_urodzenia = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        ImageView imgView = (ImageView) customView.findViewById(R.id.imgView);


        nazwa.setText("Kon: " + ((singleVetRequest.getHorse() == null) ? "" : singleVetRequest.getHorse().getName()));
        pseudonim.setText("Weterynarz: " + ((singleVetRequest.getveterinary()==null) ? "" : singleVetRequest.getveterinary().toString()));
        data_urodzenia.setText("Zgłoszone przez : " + ((singleVetRequest.getreportedBy() == null) ? "" : singleVetRequest.getreportedBy().toString()));

        if(singleVetRequest.getPictureUrl() != null && singleVetRequest.getPictureUrl() != "") {
            Picasso.with(getContext()).load(Utils.URLFORAPI + singleVetRequest.getPictureUrl()).into(imgView);
        }else{
            imgView.setImageResource(R.drawable.ic_menu_camera);
        }

        return customView;
    }
}

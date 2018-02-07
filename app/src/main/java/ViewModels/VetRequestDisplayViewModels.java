package ViewModels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.Pracownik;
import Models.VetRequest;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-12-10.
 */

public class VetRequestDisplayViewModels extends Fragment{

    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.displayvetrequestfragment, container, false);
        VetRequest vtRequest = (VetRequest) getActivity().getIntent().getSerializableExtra(Utils.vetRequestStat);

        TextView val1 = (TextView) myView.findViewById(R.id.horsedsp_val2);
        TextView val2 = (TextView) myView.findViewById(R.id.horsedsp_val1);
        TextView val3 = (TextView) myView.findViewById(R.id.horsedsp_val3);
        TextView val4 = (TextView) myView.findViewById(R.id.horsedsp_val4);
        TextView val5 = (TextView) myView.findViewById(R.id.horsedsp_val5);
        TextView val6 = (TextView) myView.findViewById(R.id.horsedsp_val6);
        TextView val7 = (TextView) myView.findViewById(R.id.horsedsp_val7);
        TextView val8 = (TextView) myView.findViewById(R.id.horsedsp_val8);
        ImageView imgView = (ImageView) myView.findViewById(R.id.imageViewHorseAcc);

        val1.setText("Kon: " + ((vtRequest.getHorse()==null) ? "" : vtRequest.getHorse().getName()));
        val2.setText("Weterynarz: " + ((vtRequest.getveterinary()==null) ? "" : vtRequest.getveterinary().toString()));
        val3.setText("Zgłoszone przez: " + ((vtRequest.getreportedBy() == null) ? "" : vtRequest.getreportedBy().toString()));
        val4.setText("Status: " + vtRequest.getStatus());
        val5.setText("Data Zgłoszenia: " + HelperMethods.getStringFromDate(vtRequest.getcreateDate()));
        val7.setText("Miejsce problemu: " + vtRequest.getdescriptionAboutPlaceWhereHorseWasInjured());
        val6.setText("Priorytet: " + vtRequest.getPriority());
        val8.setText("Opis: " + vtRequest.getnoteAboutReport());

        if(vtRequest.getPictureUrl() != null && vtRequest.getPictureUrl() != "") {
            Picasso.with(getContext()).load(Utils.URLFORAPI + vtRequest.getPictureUrl()).into(imgView);
        }else{
            imgView.setImageResource(R.drawable.ic_menu_camera);
        }

        return myView;
    }
}

package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import HelperClasses.HelperMethods;
import Models.Kon;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class HorseAdapter extends ArrayAdapter<Kon> {

    private Bitmap bt;

    public HorseAdapter(@NonNull Context context, ArrayList<Kon> horse) {
        super(context, R.layout.custom_row,horse);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row,parent,false);

        final Kon singleHorse = getItem(position);
        TextView nazwa = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView pseudonim = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView data_urodzenia = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        ImageView imgView = (ImageView) customView.findViewById(R.id.imgView);



        nazwa.setText("Imie: " + singleHorse.getName());
        pseudonim.setText("Charakter: " + singleHorse.getCharacter());
        data_urodzenia.setText("Data Urodzenia: " + HelperMethods.getStringFromDate(singleHorse.getDateOfBirth()));

        if(singleHorse.getMainPicture() != null && singleHorse.getMainPicture() != "") {
            Picasso.with(getContext()).load(singleHorse.getMainPicture()).into(imgView);
        }else{
            imgView.setImageResource(R.drawable.ic_menu_camera);
        }

        return customView;
    }
}



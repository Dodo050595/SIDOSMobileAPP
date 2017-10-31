package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import HelperClasses.Utils;
import Models.VetRequest;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-10-22.
 */

public class HorseGalleryAdapter  extends ArrayAdapter<String> {
    public HorseGalleryAdapter(@NonNull Context context, ArrayList<String> urlOfPhoto) {
        super(context, R.layout.custom_row_image,urlOfPhoto);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row_image,parent,false);

        String url = Utils.URLFORAPI + getItem(position);

        ImageView imgView = (ImageView) customView.findViewById(R.id.imgViewCustomRow);

        Picasso.with(getContext()).load(url).into(imgView);

        return customView;
    }
}

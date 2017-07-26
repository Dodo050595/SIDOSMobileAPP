package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Models.Kon;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by Dominik Deja on 11.06.2017.
 */

public class HorseFilterAdapter extends ArrayAdapter<Kon> implements Filterable {
    Context context;
    int resource, textViewResourceId;
    List<Kon> items, tempItems, suggestions;

    public HorseFilterAdapter(Context context, int resource, int textViewResourceId, List<Kon> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Kon>(items); // this makes the difference.
        suggestions = new ArrayList<Kon>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_horse, parent, false);
        }
        Kon kn = items.get(position);
        if (kn != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(kn.getName() + " ("+kn.getCharacter()+")" );
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Kon) resultValue).getName();
            str += " " + ((Kon) resultValue).getCharacter();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                suggestions.clear();
                for (Kon kn : tempItems) {
                    if (kn.getName().toLowerCase().contains(constraint.toString().toLowerCase()) || kn.getCharacter().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(kn);
                    }
                }

                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Kon> filterList = (ArrayList<Kon>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Kon kn : filterList) {
                    add(kn);
                    notifyDataSetChanged();
                }
            }
        }
    };
}

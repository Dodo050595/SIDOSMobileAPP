package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Kon;
import Models.Pracownik;
import Models.Task;
import pl.edu.s12898pjwstk.sidosmobile.R;

/**
 * Created by dejad on 2017-11-20.
 */

public class TasksAdapter extends ArrayAdapter<Task> {

    public static int numberOfTask = 0;

    public TasksAdapter(@NonNull Context context, ArrayList<Task> tsk) {
        super(context, R.layout.custom_row,tsk);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.customrowwithoutphoto,parent,false);

        String horseNames = "Kon: ";

        final Task tsk = getItem(position);
        Button accBTN = (Button) customView.findViewById(R.id.Accept_Button_Task);
        Button rejBTN = (Button) customView.findViewById(R.id.Reject_Button_Task);
        TextView firstValue = (TextView) customView.findViewById(R.id.Row_FirstValue);
        TextView secondValue = (TextView) customView.findViewById(R.id.Row_SecondValue);
        TextView thirdValue  = (TextView) customView.findViewById(R.id.Row_ThirdValue);
        TextView fourthValue  = (TextView) customView.findViewById(R.id.Row_FourthValue);
        TextView FifthValue  = (TextView) customView.findViewById(R.id.Row_FifthValueComment);

        TextView title  = (TextView) customView.findViewById(R.id.TaskNumber);

        firstValue.setText("Typ: " + tsk.getType());
        for(Kon kn : tsk.getRefersTo()){
            horseNames += kn.getName();
        }
        secondValue.setText(horseNames);
        thirdValue.setText("Instruktor: " + tsk.getAssignedTo().toString());
        fourthValue.setText("Dawka: " + tsk.getDosage());
        FifthValue.setText("Opis: " + tsk.getDescription());
        numberOfTask++;
        title.setText("Zadanie: " + numberOfTask);


        return customView;
    }
}

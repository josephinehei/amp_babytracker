package com.example.babytracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddItemFragment extends DialogFragment{
    private TrackerAdapter trackerAdapter;

    TextView category;
    EditText date;
    EditText startTime;
    EditText stopTime;
    EditText ounces;
    EditText diaperType;
    EditText diaperColor;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    String categoryChoice;

    public AddItemFragment(Context context, TrackerAdapter adapter, String categoryChoice){
        this.context = context;
        trackerAdapter = adapter;
        this.categoryChoice = categoryChoice;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView;
        if(categoryChoice == "Sleep"){
            dialogView = inflater.inflate(R.layout.dialog_tracker_sleep, null);
        } else if(categoryChoice == "Food"){
            dialogView = inflater.inflate(R.layout.dialog_tracker_food, null);
        } else{
            dialogView = inflater.inflate(R.layout.dialog_tracker_diaper, null); }

        category = (TextView) dialogView.findViewById(R.id.editCategory);
        category.setText(categoryChoice);
        date = (EditText) dialogView.findViewById(R.id.editDate);
        startTime = (EditText) dialogView.findViewById(R.id.editStartTime);
        stopTime = (EditText) dialogView.findViewById(R.id.editStopTime);
        ounces = (EditText) dialogView.findViewById(R.id.editOunces);
        diaperType = (EditText) dialogView.findViewById(R.id.editDiaperType);
        diaperColor = (EditText) dialogView.findViewById(R.id.editDiaperColor);
        notes = (EditText) dialogView.findViewById(R.id.editNotes);

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dBH = new DatabaseHelper(context);

                        String enteredCategory = categoryChoice;
                        String enteredDate = date.getText().toString().trim();
                        String enteredStartTime = startTime.getText().toString().trim();
                        String enteredStopTime = stopTime.getText().toString().trim();
                        Float enteredOunces = Float.parseFloat(ounces.getText().toString());
                        String enteredDiaperType = diaperType.getText().toString().trim();
                        String enteredDiaperColor = diaperColor.getText().toString().trim();
                        String enteredNotes = notes.getText().toString().trim();

                                        ((DatabaseHelper) dBH).insertData(
                                                enteredCategory,
                                                enteredDate,
                                                enteredStartTime,
                                                enteredStopTime,
                                                enteredOunces,
                                                enteredDiaperType,
                                                enteredDiaperColor,
                                                enteredNotes
                                        );
                                        trackerAdapter.refreshList();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddItemFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


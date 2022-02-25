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

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UpdateItemFragment extends DialogFragment {
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

    Integer _id;

    public UpdateItemFragment(Context context, TrackerAdapter adapter, Integer _id){
        this.context = context;
        trackerAdapter = adapter;
        this._id = _id;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView;
        if(category.getText() == "Sleep"){
            dialogView = inflater.inflate(R.layout.dialog_tracker_sleep, null);
        } else if(category.getText() == "Food"){
            dialogView = inflater.inflate(R.layout.dialog_tracker_food, null);
        } else{
            dialogView = inflater.inflate(R.layout.dialog_tracker_diaper, null); }
        dBH = new DatabaseHelper(context);
        BabyTracker record = ((DatabaseHelper) dBH).getRecord(_id);

        category = (TextView) dialogView.findViewById(R.id.editCategory);
        category.setText(record.getCategory());
        date = (EditText) dialogView.findViewById(R.id.editDate);
        date.setText(record.getDate());
        startTime = (EditText) dialogView.findViewById(R.id.editStartTime);
        startTime.setText(record.getStartTime());
        stopTime = (EditText) dialogView.findViewById(R.id.editStopTime);
        stopTime.setText(record.getStopTime());
        ounces = (EditText) dialogView.findViewById(R.id.editOunces);
        ounces.setText(record.getOunces().toString());
        diaperType = (EditText) dialogView.findViewById(R.id.editDiaperType);
        diaperType.setText(record.getDiaperType());
        diaperColor = (EditText) dialogView.findViewById(R.id.editDiaperColor);
        diaperColor.setText(record.getDiaperColor());
        notes = (EditText) dialogView.findViewById(R.id.editNotes);
        notes.setText(record.getNotes());

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dBH = new DatabaseHelper(context);

                        String enteredCategory = category.getText().toString().trim();
                        String enteredDate = date.getText().toString().trim();
                        String enteredStartTime = startTime.getText().toString().trim();
                        String enteredStopTime = stopTime.getText().toString().trim();
                        Float enteredOunces = Float.parseFloat(ounces.getText().toString());
                        String enteredDiaperType = diaperType.getText().toString().trim();
                        String enteredDiaperColor = diaperColor.getText().toString().trim();
                        String enteredNotes = notes.getText().toString().trim();

                        ((DatabaseHelper) dBH).updateTacker(
                                _id,
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
                        UpdateItemFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


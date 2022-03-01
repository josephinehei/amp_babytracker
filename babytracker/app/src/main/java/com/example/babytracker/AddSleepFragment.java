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

public class AddSleepFragment extends DialogFragment{
    private SleepTrackerAdapter sleepTrackerAdapter;

    TextView category;
    EditText date;
    EditText startTime;
    EditText stopTime;
    EditText notes;
    Context context;
    SQLiteOpenHelper dBH;

    public AddSleepFragment(Context context, SleepTrackerAdapter adapter){
        this.context = context;
        sleepTrackerAdapter = adapter;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_sleep, null);

        category = (TextView) dialogView.findViewById(R.id.sleepEditCategory);
        category.setText("Sleep");
        date = (EditText) dialogView.findViewById(R.id.sleepEditDate);
        startTime = (EditText) dialogView.findViewById(R.id.sleepEditStartTime);
        stopTime = (EditText) dialogView.findViewById(R.id.sleepEditStopTime);
        notes = (EditText) dialogView.findViewById(R.id.sleepEditNotes);

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dBH = new DatabaseHelper(context);

                        String enteredCategory = category.getText().toString().trim();
                        String enteredDate = date.getText().toString().trim();
                        String enteredStartTime = startTime.getText().toString().trim();
                        String enteredStopTime = stopTime.getText().toString().trim();
                        String enteredNotes = notes.getText().toString().trim();

                                        ((DatabaseHelper) dBH).insertDataSleep(
                                                enteredCategory,
                                                enteredDate,
                                                enteredStartTime,
                                                enteredStopTime,
                                                enteredNotes
                                        );
                                        sleepTrackerAdapter.refreshList();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddSleepFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


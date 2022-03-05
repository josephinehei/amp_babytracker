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

public class UpdateSleepFragment extends DialogFragment {
    private SleepTrackerAdapter sleepTrackerAdapter;

    TextView category;
    EditText date;
    EditText startTime;
    EditText stopTime;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    Integer _id;

    public UpdateSleepFragment(Context context, SleepTrackerAdapter adapter, Integer _id){
        this.context = context;
        sleepTrackerAdapter = adapter;
        this._id = _id;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_sleep, null);
        dBH = new DatabaseHelper(context);
        SleepTracker record = ((DatabaseHelper) dBH).getSleepRecord(_id);

        category = (TextView) dialogView.findViewById(R.id.sleepEditCategory);
        category.setText(record.getCategory());
        date = (EditText) dialogView.findViewById(R.id.sleepEditDate);
        date.setText(record.getDate());
        startTime = (EditText) dialogView.findViewById(R.id.sleepEditStartTime);
        startTime.setText(record.getStartTime());
        stopTime = (EditText) dialogView.findViewById(R.id.sleepEditStopTime);
        stopTime.setText(record.getStopTime());
        notes = (EditText) dialogView.findViewById(R.id.sleepEditNotes);
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
                        String enteredNotes = notes.getText().toString().trim();

                        ((DatabaseHelper) dBH).updateTrackerSleep(
                                _id,
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
                        UpdateSleepFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


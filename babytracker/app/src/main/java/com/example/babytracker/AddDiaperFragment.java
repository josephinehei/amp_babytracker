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

public class AddDiaperFragment extends DialogFragment{
    private DiaperTrackerAdapter diaperTrackerAdapter;

    TextView category;
    EditText date;
    EditText time;
    EditText diaperType;
    EditText diaperColor;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    public AddDiaperFragment(Context context, DiaperTrackerAdapter adapter){
        this.context = context;
        diaperTrackerAdapter = adapter;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_diaper, null);

        category = (TextView) dialogView.findViewById(R.id.diaperEditCategory);
        category.setText("Diaper");
        date = (EditText) dialogView.findViewById(R.id.diaperEditDate);
        time = (EditText) dialogView.findViewById(R.id.diaperEditTime);
        diaperType = (EditText) dialogView.findViewById(R.id.diaperEditDiaperType);
        diaperColor = (EditText) dialogView.findViewById(R.id.diaperEditDiaperColor);
        notes = (EditText) dialogView.findViewById(R.id.diaperEditNotes);

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dBH = new DatabaseHelper(context);

                        String enteredCategory = category.getText().toString().trim();
                        String enteredDate = date.getText().toString().trim();
                        String enteredTime = time.getText().toString().trim();
                        String enteredDiaperType = diaperType.getText().toString().trim();
                        String enteredDiaperColor = diaperColor.getText().toString().trim();
                        String enteredNotes = notes.getText().toString().trim();

                                        ((DatabaseHelper) dBH).insertDataDiaper(
                                                enteredCategory,
                                                enteredDate,
                                                enteredTime,
                                                enteredDiaperType,
                                                enteredDiaperColor,
                                                enteredNotes
                                        );
                                        diaperTrackerAdapter.refreshList();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDiaperFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


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

public class UpdateDiaperFragment extends DialogFragment {
    private DiaperTrackerAdapter diaperTrackerAdapter;

    TextView category;
    EditText date;
    EditText time;
    EditText diaperType;
    EditText diaperColor;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    Integer _id;

    public UpdateDiaperFragment(Context context, DiaperTrackerAdapter adapter, Integer _id){
        this.context = context;
        diaperTrackerAdapter = adapter;
        this._id = _id;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_diaper, null);
        dBH = new DatabaseHelper(context);
        DiaperTracker record = ((DatabaseHelper) dBH).getDiaperRecord(_id);

        category = (TextView) dialogView.findViewById(R.id.diaperEditCategory);
        category.setText(record.getCategory());
        date = (EditText) dialogView.findViewById(R.id.diaperEditDate);
        date.setText(record.getDate());
        time = (EditText) dialogView.findViewById(R.id.diaperEditTime);
        time.setText(record.getTime());
        diaperType = (EditText) dialogView.findViewById(R.id.diaperEditDiaperType);
        diaperType.setText(record.getDiaperType());
        diaperColor = (EditText) dialogView.findViewById(R.id.diaperEditDiaperColor);
        diaperColor.setText(record.getDiaperColor());
        notes = (EditText) dialogView.findViewById(R.id.diaperEditNotes);
        notes.setText(record.getNotes());

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

                        ((DatabaseHelper) dBH).updateTrackerDiaper(
                                _id,
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
                        UpdateDiaperFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


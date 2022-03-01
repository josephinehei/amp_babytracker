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

public class UpdateFoodFragment extends DialogFragment {
    private FoodTrackerAdapter foodTrackerAdapter;

    TextView category;
    EditText date;
    EditText time;
    EditText ounces;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    Integer _id;

    public UpdateFoodFragment(Context context, FoodTrackerAdapter adapter, Integer _id){
        this.context = context;
        foodTrackerAdapter = adapter;
        this._id = _id;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_food, null);
        dBH = new DatabaseHelper(context);
        FoodTracker record = ((DatabaseHelper) dBH).getFoodRecord(_id);

        category = (TextView) dialogView.findViewById(R.id.foodEditCategory);
        category.setText(record.getCategory());
        date = (EditText) dialogView.findViewById(R.id.foodEditDate);
        date.setText(record.getDate());
        time = (EditText) dialogView.findViewById(R.id.foodEditTime);
        time.setText(record.getTime());
        ounces = (EditText) dialogView.findViewById(R.id.foodEditOunces);
        ounces.setText(record.getOunces().toString());
        notes = (EditText) dialogView.findViewById(R.id.foodEditNotes);
        notes.setText(record.getNotes());

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dBH = new DatabaseHelper(context);

                        String enteredCategory = category.getText().toString().trim();
                        String enteredDate = date.getText().toString().trim();
                        String enteredTime = time.getText().toString().trim();
                        Float enteredOunces = Float.parseFloat(ounces.getText().toString());
                        String enteredNotes = notes.getText().toString().trim();

                        ((DatabaseHelper) dBH).updateTackerFood(
                                _id,
                                enteredCategory,
                                enteredDate,
                                enteredTime,
                                enteredOunces,
                                enteredNotes
                        );
                        foodTrackerAdapter.refreshList();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UpdateFoodFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


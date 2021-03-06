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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddFoodFragment extends DialogFragment{
    private FoodTrackerAdapter foodTrackerAdapter;

    TextView category;
    EditText date;
    EditText time;
    EditText ounces;
    EditText notes;

    Context context;
    SQLiteOpenHelper dBH;

    public AddFoodFragment(Context context, FoodTrackerAdapter adapter){
        this.context = context;
        foodTrackerAdapter = adapter;
    }

    //Opens a dialog box for adding a Food item with category, date and time automatically
    // filled in. Adds it to the database when "Save" is clicked. Or does nothing when "Cancel"
    // is clicked.
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_food, null);

        DateFormat df = new SimpleDateFormat("MM/dd");
        String today = df.format(Calendar.getInstance().getTime());
        DateFormat tdf = new SimpleDateFormat("hh:mm a");
        String timeNow = tdf.format(Calendar.getInstance().getTime());


        category = (TextView) dialogView.findViewById(R.id.foodEditCategory);
        category.setText("Food");
        date = (EditText) dialogView.findViewById(R.id.foodEditDate);
        date.setText(today);
        time = (EditText) dialogView.findViewById(R.id.foodEditTime);
        time.setText(timeNow);
        ounces = (EditText) dialogView.findViewById(R.id.foodEditOunces);
        notes = (EditText) dialogView.findViewById(R.id.foodEditNotes);

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

                                        ((DatabaseHelper) dBH).insertDataFood(
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
                        AddFoodFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}


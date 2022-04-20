package com.example.babytracker;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DiaperFragment extends Fragment {
    RecyclerView dRecyclerView;
    static DiaperTrackerAdapter dAdapter;
    SQLiteOpenHelper dBH;

    public DiaperFragment() {
        // Required empty public constructor
    }


    public static DiaperFragment newInstance() {
        DiaperFragment fragment = new DiaperFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Refresh recycler view when opening back up the app
    public void onResume() {
        super.onResume();
        dAdapter.refreshList();
    }
    //Uses a recycler view to display the Diaper tables from the database
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        try {
            dBH = new DatabaseHelper(view.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List listOfItems = ((DatabaseHelper) dBH).getAllDiaperRecords();

        //setup the RecyclerView
        dRecyclerView = view.findViewById(R.id.list);
        dRecyclerView.setHasFixedSize(true);
        dRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dAdapter = new DiaperTrackerAdapter(listOfItems, R.layout.fragment_diaper, view.getContext());
        dRecyclerView.setAdapter(dAdapter);
        return view;
    }

    public static DiaperTrackerAdapter giveAdapter(){
        return dAdapter;
    }
}
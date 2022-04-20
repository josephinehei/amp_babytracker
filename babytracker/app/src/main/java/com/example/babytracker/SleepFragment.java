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

public class SleepFragment extends Fragment {
    RecyclerView sRecyclerView;
    static SleepTrackerAdapter sAdapter;
    SQLiteOpenHelper dBH;

    public SleepFragment() {
        // Required empty public constructor
    }

    public static SleepFragment newInstance() {
        SleepFragment fragment = new SleepFragment();
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
        sAdapter.refreshList();
    }

    //Uses a recycler view to display the Sleep tables from the database
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        try {
            dBH = new DatabaseHelper(view.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List listOfItems = ((DatabaseHelper) dBH).getAllSleepRecords();

        //setup the RecyclerView
        sRecyclerView = view.findViewById(R.id.list);
        sRecyclerView.setHasFixedSize(true);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        sRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sAdapter = new SleepTrackerAdapter(listOfItems, R.layout.fragment_sleep, view.getContext());
        sRecyclerView.setAdapter(sAdapter);
        return view;
    }

    public static SleepTrackerAdapter giveAdapter(){
        return sAdapter;
    }
}
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
    RecyclerView mRecyclerView;
    static DiaperTrackerAdapter mAdapter;
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
        //remove this chunk before final check in
        if(listOfItems.isEmpty()){
            ((DatabaseHelper) dBH).insertDataDiaper("Diaper", "1/30", "11:36", "Poop", "Brown", "test");
            listOfItems = ((DatabaseHelper) dBH).getAllDiaperRecords();}

        //setup the RecyclerView
        mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new DiaperTrackerAdapter(listOfItems, R.layout.fragment_diaper, view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public static DiaperTrackerAdapter giveAdapter(){
        return mAdapter;
    }
}
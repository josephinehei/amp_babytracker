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

public class FoodFragment extends Fragment {
    RecyclerView mRecyclerView;
    FoodTrackerAdapter mAdapter;
    SQLiteOpenHelper dBH;

    public FoodFragment() {
        // Required empty public constructor
    }


    public static FoodFragment newInstance() {
        FoodFragment fragment = new FoodFragment();
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
        List listOfItems = ((DatabaseHelper) dBH).getAllFoodRecords();
        //remove this chunk before final check in
        if(listOfItems.isEmpty()){
            ((DatabaseHelper) dBH).insertDataFood("Food", "1/30", "11:36", 5F, "test");
            listOfItems = ((DatabaseHelper) dBH).getAllFoodRecords();}

        //setup the RecyclerView
        mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FoodTrackerAdapter(listOfItems, R.layout.fragment_food, view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public FoodTrackerAdapter giveAdapter(){
        return mAdapter;
    }
}
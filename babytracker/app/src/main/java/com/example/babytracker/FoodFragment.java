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
    RecyclerView fRecyclerView;
    static FoodTrackerAdapter fAdapter;
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
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    //Refresh recycler view when opening back up the app
    public void onResume() {
        super.onResume();
        fAdapter.refreshList();
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

        //setup the RecyclerView
        fRecyclerView = view.findViewById(R.id.list);
        fRecyclerView.setHasFixedSize(true);
        fRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fAdapter = new FoodTrackerAdapter(listOfItems, R.layout.fragment_food, view.getContext());
        fRecyclerView.setAdapter(fAdapter);
        return view;
    }

    public static FoodTrackerAdapter giveAdapter(){
        return fAdapter;
    }
}
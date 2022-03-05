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
import android.content.DialogInterface;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.List;

public class SleepFragment extends Fragment {
    RecyclerView mRecyclerView;
    static SleepTrackerAdapter mAdapter;
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
        //remove this chunk before final check in
        if(listOfItems.isEmpty()){
            ((DatabaseHelper) dBH).insertDataSleep("Sleep", "1/30", "11:36", "3:12", "test");
            listOfItems = ((DatabaseHelper) dBH).getAllSleepRecords();}

        //setup the RecyclerView
        mRecyclerView = view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SleepTrackerAdapter(listOfItems, R.layout.fragment_sleep, view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public static SleepTrackerAdapter giveAdapter(){
        return mAdapter;
    }
}
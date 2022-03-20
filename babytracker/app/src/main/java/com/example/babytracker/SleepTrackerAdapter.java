package com.example.babytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SleepTrackerAdapter extends RecyclerView.Adapter<SleepTrackerAdapter.ViewHolder>{

    private List<SleepTracker> myList;
    private int rowLayout;
    private Context sContext;
    private SQLiteOpenHelper dBH;
    private SQLiteDatabase db;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvDate;
        TextView tvStartTime;
        TextView tvStopTime;
        TextView tvNotes;
        Button btnRemove;
        Button btnEdit;

        ViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.sleepCategory);
            tvDate = itemView.findViewById(R.id.sleepDate);
            tvStartTime = itemView.findViewById(R.id.sleepStartTime);
            tvStopTime = itemView.findViewById(R.id.sleepStopTime);
            tvNotes = itemView.findViewById(R.id.sleepNotes);
            btnRemove = itemView.findViewById(R.id.sleepBtnRemove);
            btnEdit = itemView.findViewById(R.id.sleepBtnEdit);
        }
    }

    public SleepTrackerAdapter(List<SleepTracker> myList, int rowLayout, Context context){
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.sContext = context;
        dBH = new DatabaseHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        SleepTracker entry = myList.get(i);
        viewHolder.tvCategory.setText(entry.getCategory());
        viewHolder.tvDate.setText(entry.getDate());
        viewHolder.tvStartTime.setText(entry.getStartTime());
        viewHolder.tvStopTime.setText(entry.getStopTime());
        viewHolder.tvNotes.setText(entry.getNotes());

        viewHolder.btnRemove.setFocusableInTouchMode(false);
        viewHolder.btnRemove.setFocusable(false);
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SleepTracker entry = (SleepTracker) v.getTag();
                myList.remove(entry);
                ((DatabaseHelper) dBH).deleteSleepDataById(entry.getId());
                notifyDataSetChanged();
            }
        });

        viewHolder.btnEdit.setFocusableInTouchMode(false);
        viewHolder.btnEdit.setFocusable(false);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SleepTracker entry = (SleepTracker) v.getTag();
                MainActivity.getInstance().openUpdateItemDialog(entry.getId(), "Sleep");
            }
        });

        viewHolder.btnRemove.setTag(entry);
        viewHolder.btnEdit.setTag(entry);
    }

    @Override
    public int getItemCount(){
        return myList == null ? 0 : myList.size();
    }

    public void refreshList() {
        myList = ((DatabaseHelper) dBH).getAllSleepRecords();
        notifyDataSetChanged();
    }

}

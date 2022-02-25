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

class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.ViewHolder>{

    private List<BabyTracker> myList;
    private int rowLayout;
    private Context mContext;
    private SQLiteOpenHelper dBH;
    private SQLiteDatabase db;
    private Cursor cursor;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvDate;
        TextView tvStartTime;
        TextView tvStopTime;
        TextView tvOunces;
        TextView tvDiaperType;
        TextView tvDiaperColor;
        TextView tvNotes;
        Button btnRemove;
        Button btnEdit;

        ViewHolder(View itemView) {
            super(itemView);
//            tvCategory = itemView.findViewById(R.id.category);
//            tvDate = itemView.findViewById(R.id.date);
//            tvStartTime = itemView.findViewById(R.id.startTime);
//            tvStopTime = itemView.findViewById(R.id.stopTime);
//            tvOunces = itemView.findViewById(R.id.ounces);
//            tvDiaperType = itemView.findViewById(R.id.diaperType);
//            tvDiaperColor = itemView.findViewById(R.id.diaperColor);
//            tvNotes = itemView.findViewById(R.id.notes);
//            btnRemove = itemView.findViewById(R.id.btnRemove);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    TrackerAdapter(List<BabyTracker> myList, int rowLayout, Context context){
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
        dBH = new DatabaseHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        BabyTracker entry = myList.get(i);
//        viewHolder.tvCategory.setText(entry.getCategory());
//        viewHolder.tvDate.setText(entry.getDate());
//        viewHolder.tvStartTime.setText(entry.getStartTime());
//        viewHolder.tvStopTime.setText(entry.getStopTime());
//        viewHolder.tvOunces.setText(entry.getOunces().toString());
//        viewHolder.tvDiaperType.setText(entry.getDiaperType());
//        viewHolder.tvDiaperColor.setText(entry.getDiaperColor());
//        viewHolder.tvNotes.setText(entry.getNotes());
//
//        viewHolder.btnRemove.setFocusableInTouchMode(false);
//        viewHolder.btnRemove.setFocusable(false);
//        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BabyTracker entry = (BabyTracker) v.getTag();
//                myList.remove(entry);
//                ((DatabaseHelper) dBH).deleteDataById(entry.getId());
//                notifyDataSetChanged();
//            }
//        });
//
//        viewHolder.btnEdit.setFocusableInTouchMode(false);
//        viewHolder.btnEdit.setFocusable(false);
//        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BabyTracker entry = (BabyTracker) v.getTag();
//                MainActivity.getInstance().openUpdateItemDialog(entry.getId());
//            }
//        });
//
//        viewHolder.btnRemove.setTag(entry);
//        viewHolder.btnEdit.setTag(entry);
    }

    @Override
    public int getItemCount(){
        return myList == null ? 0 : myList.size();
    }

    public void refreshList() {
        myList = ((DatabaseHelper) dBH).getAllRecords();
        notifyDataSetChanged();
    }

}

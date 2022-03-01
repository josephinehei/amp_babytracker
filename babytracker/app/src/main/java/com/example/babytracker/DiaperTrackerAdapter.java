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

public class DiaperTrackerAdapter extends RecyclerView.Adapter<DiaperTrackerAdapter.ViewHolder>{

    private List<DiaperTracker> myList;
    private int rowLayout;
    private Context mContext;
    private SQLiteOpenHelper dBH;
    private SQLiteDatabase db;
    private Cursor cursor;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvDate;
        TextView tvTime;
        TextView tvDiaperType;
        TextView tvDiaperColor;
        TextView tvNotes;
        Button btnRemove;
        Button btnEdit;

        ViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.diaperCategory);
            tvDate = itemView.findViewById(R.id.diaperDate);
            tvTime = itemView.findViewById(R.id.diaperTime);
            tvDiaperType = itemView.findViewById(R.id.diaperType);
            tvDiaperColor = itemView.findViewById(R.id.diaperColor);
            tvNotes = itemView.findViewById(R.id.diaperNotes);
            btnRemove = itemView.findViewById(R.id.diaperBtnRemove);
            btnEdit = itemView.findViewById(R.id.diaperBtnEdit);
        }
    }

    public DiaperTrackerAdapter(List<DiaperTracker> myList, int rowLayout, Context context){
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
        DiaperTracker entry = myList.get(i);
        viewHolder.tvCategory.setText(entry.getCategory());
        viewHolder.tvDate.setText(entry.getDate());
        viewHolder.tvTime.setText(entry.getTime());
        viewHolder.tvDiaperType.setText(entry.getDiaperType());
        viewHolder.tvDiaperColor.setText(entry.getDiaperColor());
        viewHolder.tvNotes.setText(entry.getNotes());

        viewHolder.btnRemove.setFocusableInTouchMode(false);
        viewHolder.btnRemove.setFocusable(false);
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaperTracker entry = (DiaperTracker) v.getTag();
                myList.remove(entry);
                ((DatabaseHelper) dBH).deleteDiaperDataById(entry.getId());
                notifyDataSetChanged();
            }
        });

        viewHolder.btnEdit.setFocusableInTouchMode(false);
        viewHolder.btnEdit.setFocusable(false);
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaperTracker entry = (DiaperTracker) v.getTag();
                MainActivity.getInstance().openUpdateItemDialog(entry.getId(), "Diaper");
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
        myList = ((DatabaseHelper) dBH).getAllDiaperRecords();
        notifyDataSetChanged();
    }

}

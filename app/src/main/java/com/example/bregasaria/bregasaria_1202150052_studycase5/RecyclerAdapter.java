package com.example.bregasaria.bregasaria_1202150052_studycase5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bregasaria.bregasaria_1202150052_studycase5.Model.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by BREGAS on 3/25/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Activity> mActivityList;
    private LayoutInflater inflater;

    //Membuat Konstruktor pada Class RecyclerViewAdapter
    public RecyclerAdapter(Context context, LinkedList<Activity> mActivityList){
        inflater = LayoutInflater.from(context);
        this.mActivityList = mActivityList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activity, desc, priority;
        final RecyclerAdapter mAdapter;

        public ViewHolder(View itemView, RecyclerAdapter adapter) {
            super(itemView);
            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            activity = itemView.findViewById(R.id.todo);
            desc = itemView.findViewById(R.id.desc);
            priority = itemView.findViewById(R.id.priority);
            this.mAdapter = adapter;
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View mItemView = inflater.inflate(R.layout.info, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.activity.setText(mActivityList.get(position).getTodo());
        holder.desc.setText(mActivityList.get(position).getDescription());
        holder.priority.setText(mActivityList.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    public void dismissData(int data){
        DatabaseHelper db = new DatabaseHelper(inflater.getContext());
        boolean deleted = db.delete(mActivityList.get(data));
        if (deleted){
            mActivityList.remove(data);
            this.notifyItemRemoved(data);
            Toast.makeText(inflater.getContext(),"Deleted Success",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(inflater.getContext(),"Deleted Failed",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.gloria_kimbwala.allowanceapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gloria_kimbwala on 1/6/18.
 */

public class ChoreListAdapter extends RecyclerView.Adapter<ChoreListAdapter.ViewHolder>{
    List<String> chores;

    public ChoreListAdapter(List<String> chores) {
        this.chores = chores;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_chore, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewHead.setText(chores.get(position));
    }

    @Override
    public int getItemCount() {
        return chores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
        }
    }
}

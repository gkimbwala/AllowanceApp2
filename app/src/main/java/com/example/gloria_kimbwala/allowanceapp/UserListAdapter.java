package com.example.gloria_kimbwala.allowanceapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gloria_kimbwala on 1/6/18.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{
    List<String> users;

    public UserListAdapter(List<String> users) {
        this.users = users;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String userName = users.get(position);
        holder.userName.setText(userName);
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.userName.getContext();
                Intent intent = new Intent(context, ChoreListActivity.class);
                intent.putExtra("userName", userName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.userName);
        }
    }
}

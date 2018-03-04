package com.example.gloria_kimbwala.allowanceapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

public class ChoreListActivity extends AppCompatActivity {

    private TextView nameTextview;
    private TextView allowanceTextview;
    private RecyclerView choresRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chore_list_activity);
        nameTextview = (TextView) findViewById(R.id.name);
        allowanceTextview = (TextView) findViewById(R.id.allowance);
        choresRecyclerView = (RecyclerView)findViewById(R.id.chores);

        // TODO(gkimbwala): this is a hack that allows us to cheat and run network on the main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            AllowanceServiceStuff allowanceServiceStuff = new AllowanceServiceStuff();
            AllowanceRate allowanceRate = allowanceServiceStuff.getAllowanceRate("gloria");


            allowanceTextview.setText(Long.toString(allowanceRate.allowance));
            choresRecyclerView.setAdapter(new ChoreListAdapter(allowanceRate.chores));
            choresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            Log.i("Allowance", allowanceRate.name + " gets " + allowanceRate.allowance + " for " + allowanceRate.chores);
        } catch (Exception e) {
            Log.w("Allowance", "rate lookup failed", e);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        String userName = (String) getIntent().getExtras().get("userName");
        nameTextview.setText(userName);
    }


}

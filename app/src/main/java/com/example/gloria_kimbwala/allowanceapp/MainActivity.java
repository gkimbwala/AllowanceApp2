package com.example.gloria_kimbwala.allowanceapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nameTextview;
    private TextView allowanceTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTextview = (TextView) findViewById(R.id.name);
        allowanceTextview = (TextView) findViewById(R.id.allowance);

        // TODO(gkimbwala): this is a hack that allows us to cheat and run network on the main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            AllowanceServiceStuff allowanceServiceStuff = new AllowanceServiceStuff();
            AllowanceRate allowanceRate = allowanceServiceStuff.getAllowanceRate("jesse");

            nameTextview.setText(allowanceRate.name);
            allowanceTextview.setText(Long.toString(allowanceRate.allowance));
            Log.i("Allowance", allowanceRate.name + " gets " + allowanceRate.allowance + " for " + allowanceRate.chores);
        } catch (Exception e) {
            Log.w("Allowance", "rate lookup failed", e);
        }

    }
}

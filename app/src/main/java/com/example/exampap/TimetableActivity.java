package com.example.exampap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ArrayList<HashMap<Integer, TimetableItem>> detailList;
    private RecyclerView recyclerViewDetails;


    public TimetableActivity() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        pDialog = new ProgressDialog(TimetableActivity.this);
        pDialog.setMessage("Loading Details. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        recyclerViewDetails = findViewById(R.id.recyclerViewDetails);
        recyclerViewDetails.setLayoutManager(new LinearLayoutManager(this));

        GetDataService dataService = RetrofitClientInterface.getRetrofitInstance().create(GetDataService.class);
        String unit_code = getIntent().getStringExtra("Unit Code").trim();
        //TODO this changes for the different classes
        Call<List<TimetableItem>> call = dataService.getDetails(unit_code);
        Log.d("Nyenye","At step 1: making call");
        call.enqueue(new Callback<List<TimetableItem>>() {
            @Override
            public void onResponse(Call<List<TimetableItem>> call, Response<List<TimetableItem>> response)
            {
                Log.d("Nyeye", "At step 2 Received response");
                Log.d("resp", "onResponse: " + response.toString());
                for(TimetableItem timetableItem : response.body())
                {
                    Log.d("Details", timetableItem.getGroup()+ "");
                }
                setUpRecyclerView(response.body());
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<TimetableItem>> call, Throwable t)
            {
                Log.d("Details Failure",t.getMessage());
            }
        });

    }

    @Override
    public void onPause()
    {

        super.onPause();
        if(pDialog != null)
            pDialog.dismiss();
    }

    private void setUpRecyclerView(List<TimetableItem> timetableItems)
    {
        pDialog.dismiss();
        recyclerViewDetails.setAdapter(new TimetableItemAdapter(timetableItems, getParent()));
    }
}

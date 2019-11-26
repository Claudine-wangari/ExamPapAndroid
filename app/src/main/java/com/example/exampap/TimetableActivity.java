package com.example.exampap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
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
    private List<TimetableItem> items = new ArrayList<>();
    private TimetableItemAdapter adapter;

    public TimetableActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        pDialog = new ProgressDialog(TimetableActivity.this);
        pDialog.setMessage("Loading Details. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        adapter = new TimetableItemAdapter(items, this);
        recyclerViewDetails = findViewById(R.id.recyclerViewUnitList);
        recyclerViewDetails.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDetails.setAdapter(adapter);

        GetDataService dataService = RetrofitClientInterface.getRetrofitInstance().create(GetDataService.class);
        String unit_code = getIntent().getStringExtra("Unit Code").trim();
        //TODO this changes for the different classes
        Call<TimetableDto> call = dataService.getDetails(unit_code);
        call.enqueue(new Callback<TimetableDto>() {
            @Override
            public void onResponse(Call<TimetableDto> call, Response<TimetableDto> response) {

                recyclerViewDetails.setAdapter(new TimetableItemAdapter(response.body().items,TimetableActivity.this));
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TimetableDto> call, Throwable t) {
                Log.d("Details Failure", t.getMessage());
            }
        });

    }

    @Override
    public void onPause() {

        super.onPause();
        if (pDialog != null)
            pDialog.dismiss();
    }

    private void setUpRecyclerView(List<TimetableItem> timetableItems) {
        pDialog.dismiss();
        recyclerViewDetails.setAdapter(new TimetableItemAdapter(timetableItems, getParent()));
    }

    public class TimetableDto{
        @SerializedName("ICS 4105")
        List<TimetableItem> items = new ArrayList<>();
    }
}

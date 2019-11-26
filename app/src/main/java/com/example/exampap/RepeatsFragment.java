package com.example.exampap;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepeatsFragment extends Fragment
{
    private ProgressDialog pDialog;
    private ArrayList<HashMap<Integer, UnitItem>> unitList;
    private RecyclerView recyclerViewUnitList;
    private Button detailsButton;


    public RepeatsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause()
    {

        super.onPause();
        if(pDialog != null)
            pDialog.dismiss();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View unitView = inflater.inflate(R.layout.fragment_failed_units, container, false);
//        detailsButton = unitView.findViewById(R.id.button);
//        detailsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//
//                Intent intent1 = new Intent(getActivity(),TimetableActivity.class);
//                startActivity(intent1);
//
//            }
//        });
        return unitView;
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Units. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        recyclerViewUnitList = view.findViewById(R.id.recyclerViewUnitList);
        GetDataService dataService = RetrofitClientInterface.getRetrofitInstance().create(GetDataService.class);

        //TODO this changes for the different classes
        Call<List<UnitItem>> call = dataService.getRepeatUnits();
        call.enqueue(new Callback<List<UnitItem>>() {
            @Override
            public void onResponse(Call<List<UnitItem>> call, Response<List<UnitItem>> response)
            {
                for(UnitItem unitItem : response.body())
                {
                    Log.d("UNIT",unitItem.getUnit_name()+ "");
                }
                setUpRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<UnitItem>> call, Throwable t)
            {
                Log.d("UNIT FAILURE",t.getMessage()+"");
            }
        });


    }

    private void setUpRecyclerView(List<UnitItem> unitItems)
    {
        pDialog.dismiss();
        recyclerViewUnitList.setAdapter(new UnitItemAdapter(unitItems, getActivity()));
    }


}

package com.example.exampap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService
{

    @GET("failed")
    public Call<List<UnitItem>> getAllFailedUnits();

    @GET("repeats")
    public Call<List<UnitItem>> getRepeatUnits();

    @GET("retake")
    public Call<List<UnitItem>> getRetakeUnits();

    @GET("special")
    public Call<List<UnitItem>> getSpecialUnits();

    @GET("details/{unit_code}")
    public Call<TimetableActivity.TimetableDto> getDetails(@Path(value= "unit_code" ,encoded=true) String unit_code);

}

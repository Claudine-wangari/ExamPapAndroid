package com.example.exampap;

import com.google.gson.annotations.SerializedName;

public class TimetableItem {
    @SerializedName("study_year")
    private String group;
    @SerializedName(("day_of_the_week"))
    private String day;
    private String time;
    @SerializedName("unit_name")
    private String venue;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}

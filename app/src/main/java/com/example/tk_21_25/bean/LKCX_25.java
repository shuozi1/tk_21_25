package com.example.tk_21_25.bean;

import com.google.gson.annotations.SerializedName;

public class LKCX_25 {

    private Integer roadId;
    @SerializedName("Name")
    private String name;
    private Integer state;

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

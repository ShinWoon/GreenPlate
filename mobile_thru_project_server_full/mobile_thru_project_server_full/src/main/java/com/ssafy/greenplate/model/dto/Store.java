package com.ssafy.greenplate.model.dto;

public class Store {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String phoneNum;

    public Store(Integer id, String name, Double latitude, Double longitude, String phoneNum) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNum = phoneNum;
    }

    public Store(String name, Double latitude, Double longitude, String phoneNum) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNum = phoneNum;
    }

    public Store() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

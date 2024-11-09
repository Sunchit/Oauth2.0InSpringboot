package com.example.springbootchallenge.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    private String name;

    private String id;


    @JsonProperty("phone_no")
    private String phoneNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


}

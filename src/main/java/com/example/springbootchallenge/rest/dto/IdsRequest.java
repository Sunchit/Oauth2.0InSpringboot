package com.example.springbootchallenge.rest.dto;


import java.util.List;

public class IdsRequest {
    private List<Long> ids;
    // Getters and setters


    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
package com.example.newapplication.entity;

public class Photo {
    private String name;
    private int imageId;
    public  Photo(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}

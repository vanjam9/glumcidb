package com.example.androiddevelopment.glumcidb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class Glumci extends AppCompatActivity {

    private int id;

    private String name;
    private String description;
    private double rating;

    private String filmovi;


    public Glumci(int id, String image, String name, String description, double rating, String filmovi) {
        this.id = id;

        this.name = name;
        this.description = description;
        this.rating = rating;

this.filmovi=filmovi;
    }

    public double getRating() {
        return rating;
    }

    public String getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(String filmovi) {
        this.filmovi = filmovi;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Glumci() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }






}

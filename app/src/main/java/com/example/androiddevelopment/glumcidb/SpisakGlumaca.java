package com.example.androiddevelopment.glumcidb;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 13.2.17..
 */

public class SpisakGlumaca extends AppCompatActivity {

    private int id;
    private String name;
    private List<Glumci> glumciList;

    public SpisakGlumaca() {


        glumciList = new ArrayList<>();
    }

    public SpisakGlumaca(int id, String name) {
        this.id = id;
        this.name = name;
        glumciList = new ArrayList<>();
    }

    public List<Glumci> getGlumciList() {
        return glumciList;
    }

    public void setGlumciList(List<Glumci> glumciList) {
        this.glumciList = glumciList;
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




}

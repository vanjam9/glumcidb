package com.example.androiddevelopment.glumcidb.providers;

import com.example.androiddevelopment.glumcidb.Glumci;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by androiddevelopment on 13.2.17..
 */

public class GlumciProviders {

    public static List<Glumci> getGlumciList() {


        List<Glumci> glumciList = new ArrayList<>();
        glumciList.add(new Glumci(0, "Miki Rourke.jpg", "Miki Rourke", "dobar glumac.", 5.0,"Rambo 1" +"Rambo 2"+"Rambo3"));
        glumciList.add(new Glumci(1, "dragan-nikolic.jpg", "Dragan nikolic", "dobar glumac.", 6.0,"Rambo 1" +"Rambo 2"+"Rambo3"));
        return glumciList;
    }

    public static List<String> getGlumciNames() {

        List<String> names = new ArrayList<>();
        names.add("Miki Rourke");
        names.add("Dragan Nikolic");

        return names;
    }


    public static Glumci getGlumciById(int id) {



        switch (id) {
            case 0:
                return new Glumci(0,"Miki Rourke.jpg", "Miki Rourke", "dobar glumac.", 5.0,"Rambo 1" +"Rambo 2"+"Rambo3");
            case 1:
                return new Glumci(1,"dragan-nikolic.jpg", "Dragan nikolic", "dobar glumac.", 6.0,"Rambo 1" +"Rambo 2"+"Rambo3" );

            default:
                return null;
        }
    }



}

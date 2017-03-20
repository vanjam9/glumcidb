package com.example.androiddevelopment.glumcidb.db;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 10.3.17..
 */
@DatabaseTable(tableName = Glumcib.TABLE_NAME_USERS)
public class Glumcib {

    public static final String TABLE_NAME_USERS = "products";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_DESCRIPTION = "description";
    public static final String FIELD_NAME_RATING = "rating";
    public static final String FIELD_NAME_IMAGE = "image";
    public static final String TABLE_MOVIE_MOVIES = "movies";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int Id;


    @DatabaseField(columnName = FIELD_NAME_NAME)

    private String Name;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    private String description;

    @DatabaseField(columnName = FIELD_NAME_RATING)
    private int rating;


    @ForeignCollectionField(columnName = Glumcib.TABLE_MOVIE_MOVIES, eager = true)
    private ForeignCollection<Movie> movies;


    public Glumcib() {
    }


    public int getId() {
        return Id;
    }


    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public ForeignCollection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ForeignCollection<Movie> movies) {
        this.movies = movies;
    }








    @Override
    public String toString() {
        return "Glumcib{" +
                "Name='" + Name + '\'' +
                '}';
    }
}



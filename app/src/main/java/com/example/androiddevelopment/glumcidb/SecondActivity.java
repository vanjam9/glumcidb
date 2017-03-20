package com.example.androiddevelopment.glumcidb;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.androiddevelopment.glumcidb.db.DatabaseHelper;
import com.example.androiddevelopment.glumcidb.db.Glumcib;
import com.example.androiddevelopment.glumcidb.db.Movie;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by androiddevelopment on 13.2.17..
 */

public class SecondActivity extends AppCompatActivity {


    private DatabaseHelper databaseHelper;
    private Glumcib glumcib;

    private EditText name;
    private EditText bio;
    private EditText birth;
    private EditText rating;
    private EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_priprema_detail);

        // Shows a toast message (a pop-up message)
        //   Toast toast = Toast.makeText(getBaseContext(), "Activity.onCreate()", Toast.LENGTH_SHORT);
        // toast.show();
        //}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // onStart method is a lifecycle method called after onCreate (or after onRestart when the
        // activity had been stopped, but is now again being displayed to the user)
        //@Override
        //protected void onStart() {
        //  super.onStart();

        //Toast toast = Toast.makeText(getBaseContext(), "Activity.onStart()", Toast.LENGTH_SHORT);
        //toast.show();
        //}

        int key = getIntent().getExtras().getInt(MainActivity.kurac);

        try {
            glumcib = getDatabaseHelper().getGlumcibDao().queryForId(key);

            name = (EditText) findViewById(R.id.glumci_name);
            description = (EditText) findViewById(R.id.glumci_biografija);
           //  rating = (EditText) findViewById(R.id.glumci_ocena);

            name.setText(glumcib.getName());
            description.setText(glumcib.getDescription());
//rating.setText((glumcib.getRating()));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ListView listView = (ListView) findViewById(R.id.priprema_actor_movies);

        try {

            List<Movie> list = getDatabaseHelper().getMovieDao().queryBuilder()
                    .where()
                    .eq(Movie.FIELD_NAME_USER, glumcib.getId())
                    .query();

            ListAdapter adapter = new ArrayAdapter<>(this, R.layout.list_item, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie m = (Movie) listView.getItemAtPosition(position);


                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                //OTVORI SE DIALOG UNESETE INFORMACIJE
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.priprema_add_movie);

                Button add = (Button) dialog.findViewById(R.id.add_movie);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText name = (EditText) dialog.findViewById(R.id.movie_name);
                        EditText genre = (EditText) dialog.findViewById(R.id.movie_genre);
                        EditText year = (EditText) dialog.findViewById(R.id.movie_year);

                        Movie m = new Movie();
                        m.setmName(name.getText().toString());
                        m.setmGenre(genre.getText().toString());
                        m.setmYear(year.getText().toString());
                        m.setmUser(glumcib);

                        try {
                            getDatabaseHelper().getMovieDao().create(m);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //URADITI REFRESH
                        refresh();


                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;
            case R.id.action_update:
                //POKUPITE INFORMACIJE SA EDIT POLJA

                    glumcib.setName(name.getText().toString());
                    glumcib.setDescription(description.getText().toString());


                try {
                    getDatabaseHelper().getGlumcibDao().update(glumcib);

                    Toast.makeText(this,("Actor detail updated"),Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.action_delete:
                try {
                    getDatabaseHelper().getGlumcibDao().delete(glumcib);


                    finish(); //moramo pozvati da bi se vratili na prethodnu aktivnost
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.priprema_actor_movies);

        if (listview != null){
            ArrayAdapter<Movie> adapter = (ArrayAdapter<Movie>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Movie> list = getDatabaseHelper().getMovieDao().queryBuilder()
                            .where()
                            .eq(Movie.FIELD_NAME_USER, glumcib.getId())
                            .query();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }










    //Metoda koja komunicira sa bazom podataka
    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazo podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.



















}

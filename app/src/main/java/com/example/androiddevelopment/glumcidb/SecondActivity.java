package com.example.androiddevelopment.glumcidb;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddevelopment.glumcidb.providers.GlumciProviders;
import com.example.androiddevelopment.glumcidb.R;
import java.io.IOException;
import java.io.InputStream;



/**
 * Created by androiddevelopment on 13.2.17..
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_second_linear);

        // Shows a toast message (a pop-up message)
        //   Toast toast = Toast.makeText(getBaseContext(), "Activity.onCreate()", Toast.LENGTH_SHORT);
        // toast.show();
        //}

        // onStart method is a lifecycle method called after onCreate (or after onRestart when the
        // activity had been stopped, but is now again being displayed to the user)
        //@Override
        //protected void onStart() {
        //  super.onStart();

        //Toast toast = Toast.makeText(getBaseContext(), "Activity.onStart()", Toast.LENGTH_SHORT);
        //toast.show();
        //}
        final int position = getIntent().getIntExtra("position", 0);


        TextView tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setText(GlumciProviders.getGlumciById(position).getName());

        TextView tvOpis = (TextView) findViewById(R.id.tv_description);
        tvOpis.setText(GlumciProviders.getGlumciById(position).getDescription());



        TextView tvRating = (TextView) findViewById(R.id.tv_rating);
        tvRating.setText(Double.toString(GlumciProviders.getGlumciById(position).getRating()));




        TextView tvFilmovi = (TextView) findViewById(R.id.tv_filmovi);
        tvFilmovi.setText(GlumciProviders.getGlumciById(position).getFilmovi());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Toast.makeText(this, "Action " + getString(R.string.fragment_master_action_create) + " executed.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_update:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_update) + " executed.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_delete) + " executed.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

















}

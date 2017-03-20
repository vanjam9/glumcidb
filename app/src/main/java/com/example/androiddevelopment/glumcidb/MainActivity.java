package com.example.androiddevelopment.glumcidb;

import android.animation.AnimatorSet;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.androiddevelopment.glumcidb.db.DatabaseHelper;
import com.example.androiddevelopment.glumcidb.db.Glumcib;
import com.example.androiddevelopment.glumcidb.providers.AboutDialog;
import com.example.androiddevelopment.glumcidb.providers.PripremaPrefererences;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;


import java.sql.SQLException;
import java.util.List;


// Each activity extends Activity class
public class MainActivity extends AppCompatActivity  {




    private SharedPreferences prefs;
    private DatabaseHelper databaseHelper;
    public static String kurac = "ACT";
    public static String NOTIF_STATUS = "notif_statis";

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();
        // Loads fruits from array resource

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final ListView listView = (ListView) findViewById(R.id.listaGlumaca);


        ImageView imageView = (ImageView) findViewById(R.id.iv_image);

//Loading image from below url into imageView

        Picasso.with(this)
                .load("https://i.ytimg.com/vi/Isk0K5__V7Y/hqdefault.jpg")
                .into(imageView);

        try {
            List<Glumcib> list = getDatabaseHelper().getGlumcibDao().queryForAll();

            ListAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Glumcib p = (Glumcib) listView.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(kurac, p.getId());
                    startActivity(intent);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
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








   private void addItem(){

       final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_category_layout);
       Button dodaj = (Button) dialog.findViewById(R.id.dodaj);

       dodaj.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               Glumcib glumcib = new Glumcib();

                   EditText name = (EditText) dialog.findViewById(R.id.glumci_name);
               EditText description = (EditText) dialog.findViewById(R.id.glumci_biografija);

               EditText filmovi = (EditText) dialog.findViewById(R.id.filmovi);

               glumcib.setName(name.getText().toString());
               glumcib.setDescription(description.getText().toString());







               //pozovemo metodu create da bi upisali u bazu
                   try {
                       getDatabaseHelper().getGlumcibDao().create(glumcib);
                       boolean status = prefs.getBoolean(NOTIF_STATUS, false);

                       if(status){
                           showStatusMesage("Added new actor");

                       }


                       refresh();
                       dialog.dismiss();


                   } catch (SQLException e) {
                       e.printStackTrace();
                   }

               }


       });

dialog.show();}
    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }


    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.listaGlumaca);

        if (listview != null){
            ArrayAdapter<Glumcib> adapter = (ArrayAdapter<Glumcib>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Glumcib> list = getDatabaseHelper().getGlumcibDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showStatusMesage(String message){
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("Pripremni test");
        mBuilder.setContentText(message);
       // Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        //mBuilder.setLargeIcon(bm);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu1, menu);
            return super.onCreateOptionsMenu(menu);
        }

        // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_create:
                    Toast.makeText(this, "Action " + getString(R.string.fragment_master_action_create) + " executed.", Toast.LENGTH_SHORT).show();
                    addItem();
                    break;
                case R.id.action_update:
                    Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_update) + " executed.", Toast.LENGTH_SHORT).show();
                    ImageView imageView = (ImageView) findViewById(R.id.iv_image);

//Loading image from below url into imageView

                    Picasso.with(this)
                            .load("https://i.ytimg.com/vi/Isk0K5__V7Y/hqdefault.jpg")
                            .into(imageView);
                    AlertDialog alertDialog = new AboutDialog(this).prepareDialog();
                    alertDialog.show();
                    break;
                case R.id.preferences:

                    startActivity(new Intent(MainActivity.this, PripremaPrefererences.class));




                    Toast.makeText(this, "Action " + getString(R.string.fragment_detal_action_delete) + " executed.", Toast.LENGTH_SHORT).show();
                    break;
            }

            return super.onOptionsItemSelected(item);
        }

    }
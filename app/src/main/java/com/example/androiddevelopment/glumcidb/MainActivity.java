package com.example.androiddevelopment.glumcidb;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddevelopment.glumcidb.db.DatabaseHelper;
import com.example.androiddevelopment.glumcidb.db.Glumcib;
import com.example.androiddevelopment.glumcidb.providers.GlumciProviders;
import com.example.androiddevelopment.glumcidb.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;



// Each activity extends Activity class
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }




    private DatabaseHelper databaseHelper;



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
        final List<String> GlumacNames = GlumciProviders.getGlumciNames();

        // Creates an ArrayAdaptar from the array of String
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.list_item, GlumacNames);
        ListView listView = (ListView) findViewById(R.id.listaGlumaca);

        // Assigns ArrayAdaptar to ListView
        listView.setAdapter(dataAdapter);

        // Starts the SecondActivity and sends it the selected URL as an extra data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }


        });
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
       Button choosebtn = (Button) dialog.findViewById(R.id.choose);

       choosebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Glumcib glumcib = new Glumcib();

               EditText name = (EditText)dialog.findViewById(R.id.glumci_name);

               glumcib.setName("name");


               //pozovemo metodu create da bi upisali u bazu
               try {
                   getDatabaseHelper().getProductDao().create(glumcib);
                   refresh();
                   dialog.dismiss();


               } catch (SQLException e) {
                   e.printStackTrace();
               }


           }
       });

dialog.show();}
  /*  private void addItem(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_category_layout);

        Button choosebtn = (Button) dialog.findViewById(R.id.choose);
        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final EditText productName = (EditText) dialog.findViewById(R.id.glumci_name);

        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();

                try {
                    getDatabaseHelper().getProductDao().create(glumcib);

                    refresh();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }*/

    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.listaGlumaca);

        if (listview != null){
            ArrayAdapter<Glumcib> adapter = (ArrayAdapter<Glumcib>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Glumcib> list = getDatabaseHelper().getProductDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
                    addItem();
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
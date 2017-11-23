package com.example.root.pokeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPokemons = (ListView)findViewById(R.id.lv_pokemons);

        FetchPokemonsTask task = new FetchPokemonsTask(this, lvPokemons);
        task.execute();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conf:
                Toast.makeText(this, "Abrindo as configurações...", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.maps:
                Intent it = new Intent(this, MapsActivity.class);
                startActivity(it);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

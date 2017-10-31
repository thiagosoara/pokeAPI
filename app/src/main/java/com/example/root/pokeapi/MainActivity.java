package com.example.root.pokeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPokemons = (ListView)findViewById(R.id.lv_pokemons);

        FetchPokemonsTask task = new FetchPokemonsTask(this, lvPokemons);
        task.execute();

    }
}

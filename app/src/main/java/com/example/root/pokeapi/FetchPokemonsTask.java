package com.example.root.pokeapi;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by root on 26/10/17.
 */

class FetchPokemonsTask extends AsyncTask<Void, Void, List<Pokemon>> {
    private final ListView mLvPokemons;
    Context mContext;
    private ProgressDialog dialag;

    public FetchPokemonsTask(Context context, ListView lvPokemons) {
        mContext = context;
        mLvPokemons = lvPokemons;
    }

    @Override
    protected void onPreExecute() {
        dialag = ProgressDialog.show(mContext,"Pokemons","Aguarde...");
    }

    @Override
    protected List<Pokemon> doInBackground(Void... params) {
        List<Pokemon> pokemons = null;
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/?limit=100");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.connect();

            InputStream inputStream = conn.getInputStream();

            Scanner scanner = new Scanner(inputStream);

            String json = scanner.next();

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            json = jsonArray.toString();

            Gson gson = new Gson();

            Type type = new TypeToken<List<Pokemon>>(){}.getType();
            pokemons = gson.fromJson(json, type);

            conn.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    @Override
    protected void onPostExecute(List<Pokemon> pokemons) {
        // Desfazer o progressDialog
        dialag.dismiss();
        // Carregar ListView da Activity
        ArrayAdapter<Pokemon> adapter = new ArrayAdapter<Pokemon>(mContext,android.R.layout.simple_list_item_1,pokemons);
        mLvPokemons.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

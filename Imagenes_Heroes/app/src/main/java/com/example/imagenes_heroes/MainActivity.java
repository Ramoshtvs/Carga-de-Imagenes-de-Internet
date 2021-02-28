package com.example.imagenes_heroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<pojoHeroe> listItemsPlaces;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassConnection classConnection = new ClassConnection();
        try {

            //cadena que contendra la informacion del Json
            String cadena = classConnection.execute("https://simplifiedcoding.net/demos/view-flipper/heroes.php").get();
            //Se accede al objeto
            JSONObject ob = new JSONObject( cadena);
            JSONArray arrar = ob.getJSONArray("heroes");//usar el lenght de esta
            listItemsPlaces = new ArrayList<>();


            //Se inicializan las variables para construir el recycler
            recycler= (RecyclerView) findViewById(R.id.recycler_view);


            llm = new LinearLayoutManager(this);
            recycler.setLayoutManager(llm);
            recycler.setItemAnimator(new DefaultItemAnimator());
            String chido ="";
            //EL for llena el array list con los datos que se necesitan y devyuelve el array list lleno
            for (int i = 0; i < arrar.length(); i++)
            {
                try {
                    String url = arrar.getJSONObject(i).getString("imageurl");//habemus por objeto

                    String nombre = arrar.getJSONObject(i).getString("name");//habemus por objeto

                    chido = chido + "  " + url;

                    listItemsPlaces.add(new pojoHeroe(url, nombre));

                } catch (JSONException e) {
                    Log.e("Parser JSON", e.toString());
                }
            }

            Toast.makeText(this,chido+"", Toast.LENGTH_LONG ).show();


            //Se crea y llena con los datos el recycler por medio del adaptador
            adapterHeroe adapter = new adapterHeroe(listItemsPlaces);
            recycler.setAdapter(adapter);



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "No jalo", Toast.LENGTH_LONG).show();
        }

    }



}
package com.example.dmorenoar.listviewexample.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmorenoar.listviewexample.Models.Pokemon;
import com.example.dmorenoar.listviewexample.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_selector);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Buscamos en la view el botón para el listView
        Button btnListView = findViewById(R.id.buttonListView);

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("entro");
                //Creamos el intent del primer activity al segundo
                Intent intent = new Intent(MainActivity.this,ListActivity.class);

                //Enviamos información a la siguiente pantalla
                intent.putExtra("title",new String("Lista pokemons"));

                //Envíamos desde el main el arrayList de pokemons
                intent.putParcelableArrayListExtra("pokemons", addPokemons());


                startActivity(intent);
            }
        });


        //Buscamos en la view el botón para el gridView
        Button gridView = findViewById(R.id.buttonGridView);

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos el intent del primer activity al segundo
                Intent intent = new Intent(MainActivity.this,GridActivity.class);

                intent.putExtra("title", new String("Lista pokemons"));
                intent.putParcelableArrayListExtra("pokemons", addPokemons());

                startActivity(intent);
            }
        });

        Button gridListView = findViewById(R.id.buttonGridList);

        gridListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridListViews.class);
                intent.putExtra("title", new String("Lista pokemons"));
                intent.putParcelableArrayListExtra("pokemons", addPokemons());

                startActivity(intent);
            }
        });



    }

    public ArrayList<Pokemon> addPokemons(){
        ArrayList<Pokemon> listaP = new ArrayList<>();

        listaP.add(new Pokemon("Pikachu","Eléctrico",R.mipmap.ic_pikachu));
        listaP.add(new Pokemon("Charmander","Fuego", R.mipmap.ic_charmander));
        listaP.add(new Pokemon("Squitle","Agua", R.mipmap.ic_squirtle));
        listaP.add(new Pokemon("Meowth","Normal", R.mipmap.ic_meowth));
        listaP.add(new Pokemon("Bulbasaur","Planta", R.mipmap.ic_bulbasaur));
        listaP.add(new Pokemon("Eevee","Normal", R.mipmap.ic_eevee));
        listaP.add(new Pokemon("Pikachu","Eléctrico",R.mipmap.ic_pikachu));
        listaP.add(new Pokemon("Charmander","Fuego", R.mipmap.ic_charmander));
        listaP.add(new Pokemon("Squitle","Agua", R.mipmap.ic_squirtle));
        listaP.add(new Pokemon("Meowth","Normal", R.mipmap.ic_meowth));
        listaP.add(new Pokemon("Bulbasaur","Planta", R.mipmap.ic_bulbasaur));
        listaP.add(new Pokemon("Eevee","Normal", R.mipmap.ic_eevee));

        return listaP;
    }
}

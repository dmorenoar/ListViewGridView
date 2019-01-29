package com.example.dmorenoar.listviewexample.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dmorenoar.listviewexample.Adapters.AdapterPokemon;
import com.example.dmorenoar.listviewexample.Models.Pokemon;
import com.example.dmorenoar.listviewexample.R;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Pokemon> listaPokemons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_activity);

        enforceIconBar();


        //Buscamos el gridView en el layout
        this.gridView = (GridView) findViewById(R.id.gridViewPokemons);

        Bundle bundle = getIntent().getExtras();

        if ( bundle != null){
            listaPokemons = bundle.getParcelableArrayList("pokemons");
            setTitle(bundle.getString("title"));
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Has clickado: " + listaPokemons.get(position).getNombre(), Toast.LENGTH_LONG).show();
            }
        });

        //Enlazamos el adapter personalizado usando el adapter
        AdapterPokemon myAdapter = new AdapterPokemon(GridActivity.this,R.layout.grid_view_item, listaPokemons);

        gridView.setAdapter(myAdapter);

        //Finalmente registramos el contexto
        registerForContextMenu(this.gridView);
    }

    public void enforceIconBar(){
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

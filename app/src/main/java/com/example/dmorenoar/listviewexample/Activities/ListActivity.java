package com.example.dmorenoar.listviewexample.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmorenoar.listviewexample.Adapters.AdapterPokemon;
import com.example.dmorenoar.listviewexample.Models.Pokemon;
import com.example.dmorenoar.listviewexample.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Pokemon> listaPokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

        enforceIconBar();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            setTitle(bundle.getString("title"));
            listaPokemons = bundle.getParcelableArrayList("pokemons");
        }

        listView = (ListView) findViewById(R.id.listView);


        //Creamos el adaptador para manejar los datos

        ArrayAdapter<Pokemon> myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listaPokemons);

        //Enlazamos al listView el adapter
        listView.setAdapter(myAdapter);

        //Controlamos el click de un elemento en el listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this,"Has clickado:" + listaPokemons.get(position).getNombre() ,Toast.LENGTH_LONG).show();
            }
        });


        //Enlazamos el adaptador personalizado al listView
        AdapterPokemon adapterPokemon = new AdapterPokemon(this,R.layout.list_view_item,listaPokemons);
        listView.setAdapter(adapterPokemon);

        registerForContextMenu(this.listView);

    }


    public void enforceIconBar(){
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate();


        return true;
    }
}

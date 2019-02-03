package com.example.dmorenoar.listviewexample.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmorenoar.listviewexample.Adapters.AdapterPokemon;
import com.example.dmorenoar.listviewexample.Models.Pokemon;
import com.example.dmorenoar.listviewexample.R;

import java.util.ArrayList;

public class GridListViews extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private GridView gridView;
    private AdapterPokemon myListAdapter;
    private AdapterPokemon myGridAdapter;

    private ArrayList<Pokemon> listaPokemons;

    //Items para el option menu
    private MenuItem itemListView;
    private MenuItem itemGridView;

    private int counter = 0;
    private final int SWITCH_TO_LIST_VIEW = 0;
    private final int SWITCH_TO_GRID_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_list_views);

        //Forzamos a la carga del menú
        this.enforceIconBar();

        Bundle bundle = getIntent().getExtras();

        this.listaPokemons = bundle.getParcelableArrayList("pokemons");
        setTitle(bundle.getString("title"));


        this.listView = (ListView) findViewById(R.id.listViewMix);
        this.gridView = (GridView) findViewById(R.id.gridViewMix);


        // Adjuntando el mismo método click para ambos
        this.listView.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);

        this.myGridAdapter = new AdapterPokemon(this, R.layout.grid_view_item, listaPokemons);
        this.myListAdapter = new AdapterPokemon(this, R.layout.list_view_item, listaPokemons);


        this.listView.setAdapter(myListAdapter);
        this.gridView.setAdapter(myGridAdapter);


        /*Se podría haber implementado en la clase AdapterView.OnItemClickListener
        * para que el click fuese común tanto para el listview como el gridview*/
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridListViews.this, "Has seleccionado: " + listaPokemons.get(position).getNombre(), Toast.LENGTH_LONG).show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridListViews.this, "Has seleccionado:" + listaPokemons.get(position).getNombre(), Toast.LENGTH_LONG).show();
            }
        });
*/




        //finalmente registramos el contexto para poder trabajar con ventanas
        //Asi detectamos el long click y es un action bar
        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);

    }



    /*Controlamos los clicks del menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_pokemon:
                this.addPokemon(new Pokemon("Nuevo Pokemon", "Normal", R.mipmap.ic_eevee));
                return true;
            case R.id.list_view_item:
                this.swicthListGridView(this.SWITCH_TO_LIST_VIEW);
                return true;
            case R.id.grid_view_item:
                this.swicthListGridView(this.SWITCH_TO_GRID_VIEW);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }

    //Creamos la opción en el action bar para cambiar entre grid y listView.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflamos el potion menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        //Después de inflar recogemos la referencia a los botones que nos interesan
        this.itemListView = menu.findItem(R.id.list_view_item);
        this.itemGridView = menu.findItem(R.id.grid_view_item);
        return true;

    }

    //Control de la opción para eliminar utilizando la opción del action menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Inflamos el context menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        //Antes de inflar le añadimos el header dependiendo del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Borrar a " + this.listaPokemons.get(info.position).getNombre());

        //inflamos
        inflater.inflate(R.menu.action_bar_menu, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
    //Info contiene la informacion del objeto clicakdo
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delete_item:
                this.deletePokemon(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void swicthListGridView(int option){
        //Cambiamos entre Grid y List view
        if(option == SWITCH_TO_LIST_VIEW){
            //Habilitamos el listview y ocultamos el gridview, comprovando si el listview esta oculto
            if(this.listView.getVisibility() == View.INVISIBLE){
                //1- Escondemos el gridView y enseñamos su boton en el menu de opciones
                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);

                //2-Enseñamos el listView y ocultamos su botón en el menu de opciones
                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);

            }
        } else if (option == SWITCH_TO_GRID_VIEW){
            //Cambiamos a grrid view y comprovamos si el grid view esta en modo invisible
            if(this.gridView.getVisibility() == View.INVISIBLE){

                //1 - Escondemos el listView y enseñamos su botón en el menu de opciones
                this.listView.setVisibility(View.INVISIBLE);
                this.itemListView.setVisible(true);
                //2 - Mostramos el gridview y ocultamos su botón del menu de opciones
                this.gridView.setVisibility(View.VISIBLE);
                this.itemGridView.setVisible(false);
            }

        }



    }

    private void addPokemon(Pokemon p){
        this.listaPokemons.add(p);
        //Avisamos a los adapters del cambio para que actualizen
        this.myListAdapter.notifyDataSetChanged();
        this.myGridAdapter.notifyDataSetChanged();
    }

    private void deletePokemon(int pos){
        this.listaPokemons.remove(pos);
        //Notificamos del cambio
        this.myGridAdapter.notifyDataSetChanged();
        this.myListAdapter.notifyDataSetChanged();
    }



    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(GridListViews.this, "Has seleccionado:" + listaPokemons.get(position).getNombre(), Toast.LENGTH_LONG).show();
    }
}

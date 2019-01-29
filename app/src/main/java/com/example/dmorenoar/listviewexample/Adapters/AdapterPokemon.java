package com.example.dmorenoar.listviewexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmorenoar.listviewexample.Models.Pokemon;
import com.example.dmorenoar.listviewexample.R;


import java.util.List;

public class AdapterPokemon extends BaseAdapter {

    //Creamos el contexto donde cargaremos el adaptador
    private Context context;
    private int layout;
    private List<Pokemon> pokemonList;


    public AdapterPokemon(Context context, int layout, List<Pokemon> pokemonList){
        this.context = context;
        this.layout = layout;
        this.pokemonList = pokemonList;
    }


    @Override
    public int getCount() {
        return this.pokemonList.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return this.pokemonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Creamos la vista de cada elemento
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;


        //asignamos al holder el convertView sin tener que volver a hacer el findById
        //Si esta null lo asignamos por primera vez
        if(convertView == null){
            //Inflamos la vista y la personalizamos
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(layout, null);

            holder = new ViewHolder((TextView) convertView.findViewById(R.id.namePokemon),(TextView) convertView.findViewById(R.id.tipoPokemon), (ImageView) convertView.findViewById(R.id.imagenPokemon));

            convertView.setTag(holder);

        } else{
            //Obtenemos la referencia que posteriormente hemos creado en el convertView
            //Así reciclamos su uso sin necesidad de buscar de nuevo referencias con findById
            holder = (ViewHolder) convertView.getTag();
        }

        //holder = new ViewHolder((TextView) convertView.findViewById(R.id.namePokemon),(TextView) convertView.findViewById(R.id.tipoPokemon), (ImageView) convertView.findViewById(R.id.imagenPokemon));

        //Una vez adquirido el holder de la layout podemos asignar cada elemento segun la fila
        final Pokemon currentPokemon = getItem(position);

        holder.setNamePokemon(currentPokemon.getNombre());
        holder.setTipoPokemon(currentPokemon.getTipo());
        holder.setImgPokemon(currentPokemon.getImagen());


        return convertView;
    }


    static class ViewHolder {
        private TextView namePokemon;
        private TextView tipoPokemon;
        private ImageView imgPokemon;

        public ViewHolder(TextView namePokemon, TextView tipoPokemon, ImageView imgPokemon){
            this.namePokemon = namePokemon;
            this.tipoPokemon = tipoPokemon;
            this.imgPokemon = imgPokemon;

        }


        public void setNamePokemon(String namePokemon){
            this.namePokemon.setText(namePokemon);
        }

        public void setTipoPokemon(String tipoPokemon){
            this.tipoPokemon.setText(tipoPokemon);
        }

        public void setImgPokemon(int imgPokemon){
            this.imgPokemon.setImageResource(imgPokemon);
        }


    }
}

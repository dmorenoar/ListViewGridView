package com.example.dmorenoar.listviewexample.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {

    private String nombre;
    private String tipo;
    private int imagen;


    public Pokemon(String nombre, String tipo, int imagen){
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public Pokemon(Parcel in){
        this.nombre = in.readString();
        this.tipo = in.readString();
        this.imagen = in.readInt();
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.tipo);
        dest.writeInt(this.imagen);
    }

    public static final Parcelable.Creator<Pokemon> CREATOR = new Parcelable.Creator<Pokemon>() {
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];

        }
    };


}

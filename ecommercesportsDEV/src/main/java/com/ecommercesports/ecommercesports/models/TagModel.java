package com.ecommercesports.ecommercesports.models;

public class TagModel {

    private long idTag;
    private String nombre;

    public TagModel(){
    }

    public TagModel(long id, String nombre) {
        this.idTag = id;
        this.nombre = nombre;
    }

    public long getIdTag() {
        return idTag;
    }

    public void setIdTag(long idTag) {
        this.idTag = idTag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "idTag=" + idTag +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
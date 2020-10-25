package com.ecommercesports.ecommercesports.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTag;

    @Column(name = "nombre", nullable=false, length=45)
    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Producto> productos;

    public Tag() {
    }

    public Tag(long idTag, String nombre) {
        this.idTag = idTag;
        this.nombre = nombre;
    }

    public long getId() {
        return idTag;
    }

    public void setId(long id) {
        this.idTag = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + idTag +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
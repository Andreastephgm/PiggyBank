package com.miempresa.findapp03;

import android.widget.ImageView;

public class Banco {
    private int id;
    private String nombre;
    private String surcursal;
    private String tipo;
    private String direccion;
    private double latitud;
    private double longitud;
    private String horario;
    private ImageView imagen;


    public Banco() {
    }

    public Banco(int id, String nombre, String surcursal, String tipo, String direccion, double latitud, double longitud, String horario, ImageView imagen)
    {
        this.id = id;
        this.nombre = nombre;
        this.surcursal = surcursal;
        this.tipo = tipo;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.imagen = imagen;
    }

    public Banco(String nombre, String surcursal, String tipo, String direccion, double latitud, double longitud, String horario, ImageView imagen) {
        this.nombre = nombre;
        this.surcursal = surcursal;
        this.tipo = tipo;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSurcursal() {
        return surcursal;
    }

    public void setSurcursal(String surcursal) {
        this.surcursal = surcursal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", surcursal='" + surcursal + '\'' +
                ", tipo='" + tipo + '\'' +
                ", horario='" + horario + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", imagen =" + imagen +
        '}';
    }
}

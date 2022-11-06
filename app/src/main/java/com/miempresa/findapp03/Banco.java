package com.miempresa.findapp03;

public class Banco {
    private int id;
    private String nombre;
    private String surcursal;
    private String tipo;
    private String direccion;
    private int usuario;
    private long latData;
    private long lonData;
    private String horario;


    public Banco() {
    }

    public Banco(int id, String nombre, String surcursal, String tipo, String direccion, int usuario, long latData, long lonData, String horario) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.surcursal = surcursal;
        this.direccion = direccion;
        this.usuario = usuario;
        this.latData = latData;
        this.lonData = lonData;
        this.horario = horario;
    }

    public Banco(String nombre, String surcursal, String tipo, String direccion, int usuario, long latData, long lonData, String horario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.surcursal = surcursal;
        this.direccion = direccion;
        this.usuario = usuario;
        this.latData = latData;
        this.lonData = lonData;
        this.horario = horario;
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
        return tipo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public long getLatData() {
        return latData;
    }

    public void setLatData(long latData) {
        this.latData = latData;
    }

    public long getLonData() {
        return lonData;
    }

    public void setLonData(long lonData) {
        this.lonData = lonData;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", surcursal='" + surcursal + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario=" + usuario +
                ", latData=" + latData +
                ", lonData=" + lonData +
                ", horario='" + horario + '\'' +
                '}';
    }
}

}
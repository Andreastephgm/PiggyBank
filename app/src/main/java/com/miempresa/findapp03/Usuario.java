package com.miempresa.findapp03;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private long telefono;
    private String password;

    public Usuario(){
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", password='" + password + '\'' +
                '}';
    }
}

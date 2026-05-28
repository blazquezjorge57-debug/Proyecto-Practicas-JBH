package com.levelup.model;
//1
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String passwordHash;
    private String rol;

    public Usuario() {}

    public Usuario(int idUsuario, String nombreUsuario, String passwordHash, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario [id=" + idUsuario + ", nombre=" + nombreUsuario + ", rol=" + rol + "]";
    }
}
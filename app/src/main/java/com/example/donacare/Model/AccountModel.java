package com.example.donacare.Model;

public class AccountModel {
    private String username, nama, password, email, hp, alamat, role;

    public AccountModel() {

    }

    public AccountModel(String nama, String username, String email, String password, String hp, String alamat, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nama = nama;
        this.hp = hp;
        this.alamat = alamat;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

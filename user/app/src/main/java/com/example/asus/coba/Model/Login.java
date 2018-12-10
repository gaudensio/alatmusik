package com.example.asus.coba.Model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("id_pembeli")
    private String idPembeli;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("telp")
    private String telp;
    @SerializedName("photo_url")
    private String photoUrl;


    public Login(String idPembeli, String nama, String alamat, String telp, String photoUrl) {
        this.idPembeli = idPembeli;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.photoUrl = photoUrl;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}

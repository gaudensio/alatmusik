package com.example.asus_pc.alatmusik.Model;

import com.google.gson.annotations.SerializedName;

public class Alat {
    @SerializedName("id_alat")
    private String idAlat;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("nama")
    private String nama;
    @SerializedName("harga")
    private String harga;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Alat(String idAlat, String jenis, String nama, String harga,String lokasi, String deskripsi, String photoUrl, String
            action) {
        this.idAlat = idAlat;
        this.jenis = jenis;
        this.nama = nama;
        this.harga = harga;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdAlat() {
        return idAlat;
    }

    public void setIdPembeli(String idAlat) {
        this.idAlat = idAlat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

package com.example.asus.coba.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tiket {

    @SerializedName("id_tiket")
    @Expose
    private String idTiket;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;

    // Ada perubahan nama field di REST API
    @SerializedName("tgl_brgkt")
    @Expose
    private String tanggalBerangkat;
    @SerializedName("nama_kereta")
    @Expose
    private String namaKereta;

    // Menambahkan field baru: kuota tiket
    @SerializedName("kuota")
    @Expose
    private String kuota;

    // Menambahkan field baru: harga tiket
    @SerializedName("harga_tiket")
    @Expose
    private String harga;

    /**
     *
     * @param tujuan
     * @param namaKereta
     * @param idTiket
     * @param tanggalBerangkat
     * @param kuota
     * @param harga
     */
    public Tiket(String idTiket, String tujuan, String tanggalBerangkat, String namaKereta, String
            kuota, String harga) {
        super();
        this.idTiket = idTiket;
        this.tujuan = tujuan;
        this.tanggalBerangkat = tanggalBerangkat;
        this.namaKereta = namaKereta;
        this.kuota = kuota;
        this.harga = harga;
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public Tiket withIdTiket(String idTiket) {
        this.idTiket = idTiket;
        return this;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Tiket withTujuan(String tujuan) {
        this.tujuan = tujuan;
        return this;
    }

    public String getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(String tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public Tiket withTanggalBerangkat(String tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
        return this;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public Tiket withNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
        return this;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public Tiket withKuota(String kuota) {
        this.kuota = kuota;
        return this;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Tiket withHarga(String harga) {
        this.harga = harga;
        return this;
    }

}
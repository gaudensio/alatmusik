package com.example.asus_pc.alatmusik.Model;
import com.google.gson.annotations.SerializedName;
public class Transaksi {
    @SerializedName("id_transaksi")
    private String id_transaksi;
    @SerializedName("id_alat")
    private String id_alat;
    @SerializedName("tanggal_order")
    private String tanggal_order;
    @SerializedName("id_user")
    private String id_user;

    public Transaksi(String id_transaksi, String id_user, String tanggal_order, String id_alat) {
        this.id_transaksi = id_transaksi;
        this.id_user = id_user;
        this.tanggal_order = tanggal_order;
        this.id_alat= id_alat;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTanggal_order() {
        return tanggal_order;
    }

    public void setTanggal_order(String tanggal_order) {
        this.tanggal_order = tanggal_order;
    }

    public String getId_alat() {
        return id_alat;
    }

    public void setId_alat(String id_alat) {
        this.id_alat = id_alat;
    }

}

package com.example.asus_pc.alatmusik.Model;
import com.google.gson.annotations.SerializedName;
public class PostPutDelTransaksi {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Transaksi mTransaksi;
    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaksi getTransaksi() {
        return mTransaksi;
    }

    public void setPembelian(Transaksi transaksi) {
        mTransaksi = transaksi;
    }

}

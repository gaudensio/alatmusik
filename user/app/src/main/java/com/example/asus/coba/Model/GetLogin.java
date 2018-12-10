package com.example.asus.coba.Model;

import com.google.gson.annotations.SerializedName;

// Contoh respon dari API SERVER adalah:
// array(
//  "status"  => "success",
//  "result" => "id_pembeli",
//  "message" => "User ditemukan"
// )
// untuk login ini, result JSON tidak berbentuk List array seperti pada JSON Pembeli/ Pembelian
// tetapi langsung value id_pembeli

public class GetLogin {
    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private String result;

    @SerializedName("message")
    private String message;

    public GetLogin() {}

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}

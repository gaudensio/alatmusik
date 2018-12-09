package com.example.asus_pc.alatmusik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetAlat {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Alat> result = new ArrayList<Alat>();
    @SerializedName("message")
    private String message;
    public GetAlat() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Alat> getResult() {
        return result;
    }

    public void setResult(List<Alat> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

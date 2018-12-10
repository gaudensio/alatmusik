package com.example.asus.coba.Rest;

import com.mcrury.app.andromeda.Model.GetLogin;
import com.mcrury.app.andromeda.Model.GetPembeli;
import com.mcrury.app.andromeda.Model.GetPembelian;
import com.mcrury.app.andromeda.Model.GetTiket;
import com.mcrury.app.andromeda.Model.PostPutDelPembelian;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("pembelian/user")
    Call<GetPembelian> getPembelian();

    @FormUrlEncoded
    @POST("pembelian/user")
    Call<PostPutDelPembelian> postPembelian
            (@Field("id_pembeli") String idPembeli,
             @Field("tanggal_beli") String tanggalBeli, @Field("total_harga") String totalHarga,
             @Field("id_tiket") String idTiket);

    @FormUrlEncoded
    @PUT("pembelian/user")
    Call<PostPutDelPembelian> putPembelian(
            @Field("id_pembelian") String idPembelian, @Field("id_pembeli") String idPembeli,
            @Field("tanggal_beli") String tanggalBeli, @Field("total_harga") String totalHarga,
            @Field("id_tiket") String idTiket);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pembelian/user", hasBody = true)
    Call<PostPutDelPembelian> deletePembelian(@Field("id_pembelian") String idPembelian);

    /********* PEMBELI *********/
    @GET("pembeli/all")
    Call<GetPembeli> getPembeli();

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> postPembeli(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telp,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> putPembeli(
            @Part MultipartBody.Part file,
            @Part("id_pembeli") RequestBody idPembeli,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telp,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> deletePembeli(
            @Part("id_pembeli") RequestBody idPembeli,
            @Part("action") RequestBody action);

    /********* TIKET *********/
    @GET("tiket/available")
    Call<GetTiket> getTiket();

    @FormUrlEncoded
    @POST("tiket/available")
    Call<GetTiket> getTiketforPembeli(
            @Field("id_pembeli") String idPembeli
    );

    @FormUrlEncoded
    @POST("tiket/all")
    Call<GetTiket> postTiket(
            @Field("tujuan") String tujuan,
            @Field("tanggal_berangkat") String tglBerangkat,
            @Field("nama_kereta") String namaKereta,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("tiket/all")
    Call<GetTiket> putTiket(
            @Field("id_tiket") String idTiket,
            @Field("tujuan") String tujuan,
            @Field("tanggal_berangkat") String tglBerangkat,
            @Field("nama_kereta") String namaKereta,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("tiket/all")
    Call<GetTiket> deleteTiket(
            @Field("id_tiket") String idTiket,
            @Field("action") String action);

    /********* Login *********/
    // Ingat, tambahkan dulu fungsi login_post() pada controller Pembeli di REST server 
    @FormUrlEncoded
    @POST("pembeli/login")
    Call<GetLogin> loginPembeli(
            @Field("email") String email,
            @Field("password") String password);

}

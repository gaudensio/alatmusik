package com.example.asus_pc.alatmusik.Rest;
import com.example.asus_pc.alatmusik.Model.GetAlat;
import com.example.asus_pc.alatmusik.Model.GetTransaksi;
import com.example.asus_pc.alatmusik.Model.PostPutDelTransaksi;

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
    @GET("transaksi/user")
    Call<GetTransaksi> getTransaksiCall();

    @FormUrlEncoded
    @POST("transaksi/user")
    Call<PostPutDelTransaksi> postPembelian
            (@Field("id_transaksi") String idTransaksi, @Field("id_user") String idUser,
             @Field("tanggal_order") String tanggalOrder, @Field("id_alat") String idAlat);

    @FormUrlEncoded
    @PUT("transaksi/user")
    Call<PostPutDelTransaksi> putPembelian(
            @Field("id_transaksi") String idTransaksi, @Field("id_user") String idUser,
            @Field("tanggal_order") String tanggalOrder, @Field("id_alat") String idAlat);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "transaksi/user", hasBody = true)
    Call<PostPutDelTransaksi> deleteTransaksi(@Field("id_transaksi") String idTransaksi);

    /********* ALAT MUSIK*********/
    @GET("alat/all")
    Call<GetAlat> getAlat();

    @Multipart
    @POST("alat/all")
    Call<GetAlat> postAlat(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("jenis") RequestBody jenis,
            @Part("harga") RequestBody harga,
            @Part("lokasi") RequestBody lokasi,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("alat/all")
    Call<GetAlat> putAlat(
            @Part MultipartBody.Part file,
            @Part("id_alat") RequestBody idAlat,
            @Part("nama") RequestBody nama,
            @Part("jenis") RequestBody jenis,
            @Part("harga") RequestBody harga,
            @Part("lokasi") RequestBody lokasi,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("alat/all")
    Call<GetAlat> deleteAlat(
            @Part("id_alat") RequestBody idAlat,
            @Part("action") RequestBody action);
}

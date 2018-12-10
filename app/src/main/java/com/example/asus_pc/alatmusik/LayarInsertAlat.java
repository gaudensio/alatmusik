package com.example.asus_pc.alatmusik;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_pc.alatmusik.Model.GetAlat;
import com.example.asus_pc.alatmusik.Rest.ApiClient;
import com.example.asus_pc.alatmusik.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertAlat extends AppCompatActivity {
    Context mContext;
    ImageView mImageView;
    Button btAddPhotoId, btAddBack, btAddData, btCamera;
    EditText edtAddNamaAlat, edtAddJenisAlat, edtAddHarga, edtAddLokasi, edtAddDeskripsi;
    TextView tvAddMessage;
    String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_alat);

        mContext = getApplicationContext();
        mImageView = (ImageView) findViewById(R.id.imgAddPhotoId);
        btAddPhotoId = (Button)  findViewById(R.id.btAddPhotoId);
        // edtAddIdPembeli = (EditText) findViewById(R.id.edtAddIdPembeli);
        edtAddNamaAlat = (EditText) findViewById(R.id.edtAddNamaAlat);
        edtAddJenisAlat = (EditText) findViewById(R.id.edtAddJenisAlat);
        edtAddHarga = (EditText) findViewById(R.id.edtAddHargaAlat);
        edtAddLokasi = findViewById(R.id.edtAddLokasiAlat);
        edtAddDeskripsi = findViewById(R.id.edtAddDeskripsi);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        btCamera = findViewById(R.id.btCamera);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaAlat.getText().toString().isEmpty())?"":edtAddNamaAlat.getText().toString());
                RequestBody reqJenis = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaAlat.getText().toString().isEmpty())?"":edtAddJenisAlat.getText().toString());
                RequestBody reqHarga = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaAlat.getText().toString().isEmpty())?"":edtAddHarga.getText().toString());
                RequestBody reqLokasi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaAlat.getText().toString().isEmpty())?"":edtAddLokasi.getText().toString());
                RequestBody reqDeskripsi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNamaAlat.getText().toString().isEmpty())?"":edtAddDeskripsi.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetAlat> mAlatCall = mApiInterface.postAlat(body, reqNama,
                        reqJenis, reqHarga, reqLokasi, reqDeskripsi,reqAction );
                mAlatCall.enqueue(new Callback<GetAlat>() {
                    @Override
                    public void onResponse(Call<GetAlat> call, Response<GetAlat> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")){
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body()
                                    .getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+"\n");
                        }else{
                            String detail = "\n"+
                                    "id_alat = "+response.body().getResult().get(0).getIdAlat()+"\n"+
                                    "nama = "+response.body().getResult().get(0).getNama()+"\n"+
                                    "jenis = "+response.body().getResult().get(0).getJenis()+"\n"+
                                    "harga = "+response.body().getResult().get(0).getHarga()+"\n"+
                                    "lokasi = "+response.body().getResult().get(0).getLokasi()+"\n"+
                                    "deskripsi = "+response.body().getResult().get(0).getDeskripsi()+"\n"+
                                    "photo_url = "+response.body().getResult().get(0).getPhotoUrl()
                                    +"\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = "+response.body().getStatus()+"\n"+
                                    "Message = "+response.body().getMessage()+detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAlat> call, Throwable t) {
                      Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = "+ t.getMessage
                                ());
                    }
                });
            }
        });
        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarListAlat.class);
                startActivity(intent);
            }
        });
        btAddPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

                Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
//                Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor.close();
            }else{
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }
    }
}

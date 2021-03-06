package com.example.asus_pc.alatmusik;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
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

import com.bumptech.glide.Glide;
import com.example.asus_pc.alatmusik.Model.GetAlat;
import com.example.asus_pc.alatmusik.Rest.ApiClient;
import com.example.asus_pc.alatmusik.Rest.ApiInterface;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditAlat extends AppCompatActivity {

    ImageView mPhotoUrl;
    EditText edtIdAlat, edtNama, edtJenis, edtHarga, edtLokasi, edtDeskripsi;
    TextView tvMessage;
    Context mContext;
    Button btUpdate, btDelete, btBack, btPhotoUrl, btCameraId;
    String pathImage = "";
    String idneee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_alat);

        mContext = getApplicationContext();

        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoId);
        edtIdAlat = (EditText) findViewById(R.id.edtIdAlat);
        edtNama = (EditText) findViewById(R.id.edtNamaAlat);
        edtJenis = (EditText) findViewById(R.id.edtJenisAlat);
        edtHarga = (EditText) findViewById(R.id.edtHargaAlat);
        edtLokasi = findViewById(R.id.edtLokasiAlat);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btPhotoUrl = (Button) findViewById(R.id.btPhotoId);
        btCameraId = findViewById(R.id.btCameraId);

        Intent mIntent = getIntent();

//        edtIdAlat.setText(mIntent.getStringExtra("id_alat"));
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtJenis.setText(mIntent.getStringExtra("jenis"));
        edtHarga.setText(mIntent.getStringExtra("harga"));
        edtLokasi.setText(mIntent.getStringExtra("lokasi"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));

        idneee = mIntent.getStringExtra("id_alat");
//        if (mIntent.getStringExtra("photo_url").length()>0) Picasso.with(mContext).load
// (ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
//        else Picasso.with(mContext).load(R.drawable.photoid).into(mPhotoUrl);
        if (mIntent.getStringExtra("photo_url") != null)
            Glide.with(mContext).load(ApiClient
                    .BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
        else
            Glide.with(mContext).load(R.drawable.cek).into(mPhotoUrl);

        pathImage = mIntent.getStringExtra("photo_url");
        setListener();
    }

    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultipartBody.Part body = null;
                //dicek apakah image sama dengan yang ada di server atau berubah
                //jika sama dikirim lagi jika berbeda akan dikirim ke server
                if ((!pathImage.contains("uploads/" + idneee)) &&
                        (pathImage.length() > 0)) {
                    //File creating from selected URL
                    File file = new File(pathImage);

                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(
                            MediaType.parse("multipart/form-data"), file);

                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }

                RequestBody reqIdAlat =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (idneee.isEmpty()) ?
                                        "" : idneee);

                RequestBody reqNama =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtNama.getText().toString().isEmpty()) ?
                                        "" : edtNama.getText().toString());

                RequestBody reqJenis =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtJenis.getText().toString().isEmpty()) ?
                                        "" : edtJenis.getText().toString());

                RequestBody reqHarga =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtHarga.getText().toString().isEmpty()) ?
                                        "" : edtHarga.getText().toString());

                RequestBody reqLokasi =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtLokasi.getText().toString().isEmpty()) ?
                                        "" : edtLokasi.getText().toString());

                RequestBody reqDeskripsi =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtDeskripsi.getText().toString().isEmpty()) ?
                                        "" : edtDeskripsi.getText().toString());

                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "update");

                Call<GetAlat> callUpdate = mApiInterface.putAlat(body, reqIdAlat, reqNama,
                        reqJenis, reqHarga, reqLokasi, reqDeskripsi, reqAction);

                callUpdate.enqueue(new Callback<GetAlat>() {
                    @Override
                    public void onResponse(Call<GetAlat> call, Response<GetAlat> response) {
                        //Log.d("Update Retrofit ", response.body().getStatus());
                        if (response.body().getStatus().equals("failed")) {
                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                    .getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + "\n");
                        } else {
                            String detail = "\n" +
                                    "id_alat = " + response.body().getResult().get(0).getIdAlat() + "\n" +
                                    "nama = " + response.body().getResult().get(0).getNama() + "\n" +
                                    "jenis = " + response.body().getResult().get(0).getJenis() + "\n" +
                                    "harga = " + response.body().getResult().get(0).getHarga() + "\n" +
                                    "lokasi = " + response.body().getResult().get(0).getLokasi() + "\n" +
                                    "deskripsi = " + response.body().getResult().get(0).getDeskripsi() + "\n" +
                                    "photo_url = " + response.body().getResult().get(0).getPhotoUrl()
                                    + "\n";
                            tvMessage.setText("Retrofit Update \n Status = " + response.body().getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAlat> call, Throwable t) {
                        //Log.d("Update Retrofit ", t.getMessage());
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdAlat =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (idneee.isEmpty()) ?
                                        "" : idneee);
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");

                Call<GetAlat> callDelete = mApiInterface.deleteAlat(reqIdAlat, reqAction);
                callDelete.enqueue(new Callback<GetAlat>() {
                    @Override
                    public void onResponse(Call<GetAlat> call, Response<GetAlat> response) {
                        tvMessage.setText("Retrofit Delete \n Status = " + response.body()
                                .getStatus() + "\n" +
                                "Message = " + response.body().getMessage() + "\n");
                    }

                    @Override
                    public void onFailure(Call<GetAlat> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = " + t.getMessage());
                    }
                });
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, LayarListAlat.class);
                startActivity(tempIntent);
            }
        });

        btPhotoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih foto untuk " +
                        "di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btCameraId.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isDeviceSupportCamera()) {
                            Toast.makeText(getApplicationContext(),"Camera di device anda tidak tersedia",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            mintaPermissions();
                        }
                    }
                })
        );
    }

    private void mintaPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // Cek apakah semua permission yang diperlukan sudah diijinkan
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(),
                                    "Semua permissions diijinkan!", Toast.LENGTH_SHORT).show();
                            captureImage();
                        }
// Cek apakah ada permission yang tidak diijinkan
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // Info user untuk mengubah setting permission
                            Toast.makeText(mContext, "Permission harus diisi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10) {
            if (data == null) {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                pathImage = cursor.getString(columnIndex);

                //Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(pathImage)).into(mPhotoUrl);
                cursor.close();
            } else {
                Toast.makeText(mContext, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 100) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            mPhotoUrl.setImageBitmap(thumbnail);

            simpanImage(thumbnail);
            Toast.makeText(mContext, "Foto berhasil di-load dari Camera!",
                    Toast.LENGTH_SHORT).show();
    }
}


    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;

        } else {
            // no camera on this device
            return false;
        }
    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 100);
        }
    }

    private String simpanImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // Kualitas gambar yang disimpan
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        // Buat object direktori file
        File lokasiImage = new File(
                Environment.getExternalStorageDirectory() + "/praktikum");

        // Buat direktori untuk penyimpanan
        if (!lokasiImage.exists()) {
            lokasiImage.mkdirs();
        }
        try {
            // Untuk penamaan file
            File f = new File(lokasiImage, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();

            // Operasi file
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("PRAKTIKUM", "File tersimpan di --->" + f.getAbsolutePath());

            // Return file
            return f.getAbsolutePath();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


}

package com.example.asus_pc.alatmusik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus_pc.alatmusik.Model.PostPutDelTransaksi;
import com.example.asus_pc.alatmusik.Rest.ApiClient;
import com.example.asus_pc.alatmusik.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarDetail extends AppCompatActivity {
    EditText edtIdTransaksi, edtIdUser, edtTanggalOrder, edtIdAlat;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail);

        edtIdTransaksi = (EditText) findViewById(R.id.edtIdTransaksi);
        edtIdUser = (EditText) findViewById(R.id.edtIdUser);
        edtTanggalOrder = (EditText) findViewById(R.id.edtTanggalOrder);
        edtIdAlat = (EditText) findViewById(R.id.edtIdAlat);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);


        Intent mIntent = getIntent();
        edtIdTransaksi.setText(mIntent.getStringExtra("id_transaksi"));
        edtIdUser.setText(mIntent.getStringExtra("id_user"));
        edtTanggalOrder.setText(mIntent.getStringExtra("tanggal_order"));
        edtIdAlat.setText(mIntent.getStringExtra("id_alat"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelTransaksi> updateTransaksiCall = mApiInterface.putPembelian(
                        edtIdTransaksi.getText().toString(),
                        edtIdUser.getText().toString(),
                        edtTanggalOrder.getText().toString(),
                        edtIdAlat.getText().toString());

                updateTransaksiCall.enqueue(new Callback<PostPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelTransaksi> postTransaksiCall = mApiInterface.postPembelian(
                        edtIdTransaksi.getText().toString(),
                        edtIdUser.getText().toString(),
                        edtTanggalOrder.getText().toString(),
                        edtIdAlat.getText().toString());

                postTransaksiCall.enqueue(new Callback<PostPutDelTransaksi>() {
                    @Override
                    public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdTransaksi.getText().toString().trim().isEmpty()){

                    Call<PostPutDelTransaksi> deleteTransaksi = mApiInterface.deleteTransaksi(edtIdTransaksi.getText().toString());
                    deleteTransaksi.enqueue(new Callback<PostPutDelTransaksi>() {
                        @Override
                        public void onResponse(Call<PostPutDelTransaksi> call, Response<PostPutDelTransaksi> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDelTransaksi> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_pembelian harus diisi");
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
    }
}

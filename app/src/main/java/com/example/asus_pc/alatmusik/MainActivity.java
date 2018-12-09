package com.example.asus_pc.alatmusik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.asus_pc.alatmusik.Adapter.MyAdapter;
import com.example.asus_pc.alatmusik.Model.GetTransaksi;
import com.example.asus_pc.alatmusik.Model.PostPutDelTransaksi;
import com.example.asus_pc.alatmusik.Model.Transaksi;
import com.example.asus_pc.alatmusik.Rest.ApiClient;
import com.example.asus_pc.alatmusik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btGet;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = (Button) findViewById(R.id.btGet);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetTransaksi> transaksiCall = mApiInterface.getTransaksiCall();
                transaksiCall.enqueue(new Callback<GetTransaksi>() {
                    @Override
                    public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                        List<Transaksi> transaksiList = response.body().getListDataTransaksi();
                        Log.d("Retrofit Get", "Jumlah data transaksi: " + String.valueOf(transaksiList.size()));

                        mAdapter = new MyAdapter(transaksiList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetTransaksi> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.menuTambahTransaksi:
                mIntent = new Intent(this, LayarDetail.class);
                startActivity(mIntent);
                return true;

            case R.id.menuListAlat:
                mIntent = new Intent(this, LayarListAlat.class);
                startActivity(mIntent);
                return true;

            case R.id.menuInsertDataAlat:
                Intent intent = new Intent(this, LayarInsertAlat.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}


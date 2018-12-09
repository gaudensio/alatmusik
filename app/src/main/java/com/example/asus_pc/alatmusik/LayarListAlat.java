package com.example.asus_pc.alatmusik;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;

import com.example.asus_pc.alatmusik.Adapter.AlatAdapter;
import com.example.asus_pc.alatmusik.Model.Alat;
import com.example.asus_pc.alatmusik.Model.GetAlat;
import com.example.asus_pc.alatmusik.Rest.ApiClient;
import com.example.asus_pc.alatmusik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListAlat extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet,btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_alat);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btAddData = findViewById(R.id.btAddData);
        btGet = (Button) findViewById(R.id.btGet);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetAlat> mAlatCall = mApiInterface.getAlat();
                mAlatCall.enqueue(new Callback<GetAlat>() {
                    @Override
                    public void onResponse(Call<GetAlat> call, Response<GetAlat> response) {
                        Log.d("Get Alat",response.body().getStatus());
                        List<Alat> listAlat = response.body().getResult();
                        mAdapter = new AlatAdapter(listAlat);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetAlat> call, Throwable t) {
                        Log.d("Get Alat",t.getMessage());
                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertAlat.class);
                startActivity(intent);
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

            case R.id.menuListTransaksi:
                mIntent = new Intent(this, MainActivity.class);
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

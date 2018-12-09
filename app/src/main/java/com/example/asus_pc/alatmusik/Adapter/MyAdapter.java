package com.example.asus_pc.alatmusik.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_pc.alatmusik.LayarDetail;
import com.example.asus_pc.alatmusik.Model.Transaksi;
import com.example.asus_pc.alatmusik.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Transaksi> mTransaksiList;

    public MyAdapter(List<Transaksi> transaksiList) {
        mTransaksiList = transaksiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdTransaksi.setText("Id Transaksi :  " + mTransaksiList.get(position)
                .getId_transaksi());
        holder.mTextViewIdUser.setText("Id User :  " + mTransaksiList.get(position)
                .getId_user());
        holder.mTextViewIdAlat.setText("Id Alat :  " + mTransaksiList.get(position).getId_alat
                ());
        holder.mTextViewTanggal.setText("Tanggal Order :  " + mTransaksiList.get(position)
                .getTanggal_order());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LayarDetail.class);
                mIntent.putExtra("id_pembelian",mTransaksiList.get(position).getId_transaksi());
                mIntent.putExtra("id_pembeli",mTransaksiList.get(position).getId_user());
                mIntent.putExtra("tanggal_beli",mTransaksiList.get(position).getTanggal_order());
                mIntent.putExtra("id_tiket",mTransaksiList.get(position).getId_alat());
                view.getContext().startActivity(mIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransaksiList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdTransaksi, mTextViewIdUser, mTextViewTanggal, mTextViewIdAlat;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdTransaksi = (TextView) itemView.findViewById(R.id.tvIdTransaksi);
            mTextViewIdUser = (TextView) itemView.findViewById(R.id.tvIdUser);
            mTextViewTanggal = (TextView) itemView.findViewById(R.id.tvTanggalOrder);
            mTextViewIdAlat = (TextView) itemView.findViewById(R.id.tvIdAlat);
        }
    }
}

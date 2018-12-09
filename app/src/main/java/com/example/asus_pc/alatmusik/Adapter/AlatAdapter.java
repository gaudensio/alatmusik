package com.example.asus_pc.alatmusik.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus_pc.alatmusik.LayarEditAlat;
import com.example.asus_pc.alatmusik.Model.Alat;
import com.example.asus_pc.alatmusik.R;

import java.util.List;

public class AlatAdapter extends RecyclerView.Adapter<AlatAdapter.AlatViewHolder> {
    List<Alat> listAlat;

    public AlatAdapter(List<Alat> listPembeli) {
        this.listAlat = listPembeli;
    }

    @Override
    public AlatAdapter.AlatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alat, parent, false);
        AlatViewHolder mHolder = new AlatViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(AlatAdapter.AlatViewHolder holder, final int position) {

//        holder.tvIdAlat.setText(listAlat.get(position).getIdAlat());
        holder.tvNama.setText(listAlat.get(position).getNama());
        holder.tvJenis.setText(listAlat.get(position).getJenis());
        holder.tvHarga.setText(listAlat.get(position).getHarga());
        if (listAlat.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(listAlat.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {
//          Picasso.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.cek).into(holder
                    .mPhotoURL);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), LayarEditAlat.class);
                intent.putExtra("id_alat", listAlat.get(position).getIdAlat());
                intent.putExtra("nama", listAlat.get(position).getNama());
                intent.putExtra("jenis", listAlat.get(position).getJenis());
                intent.putExtra("harga", listAlat.get(position).getHarga());
                intent.putExtra("lokasi", listAlat.get(position).getLokasi());
                intent.putExtra("deskripsi", listAlat.get(position).getDeskripsi());
                intent.putExtra("photo_url", listAlat.get(position).getPhotoUrl());
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listAlat.size();
    }

    public class AlatViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvIdAlat, tvNama, tvJenis, tvHarga, tvLokasi, tvDeskripsi;

        public AlatViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgAlat);
            tvIdAlat = (TextView) itemView.findViewById(R.id.tvIdAlat);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvJenis = (TextView) itemView.findViewById(R.id.tvJenisContent);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHargaContent);
        }
    }
}

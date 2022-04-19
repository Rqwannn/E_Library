package com.example.e_library.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.Kategori_buku.DetailBuku;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.BukuModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KategoriBukuAdapter extends RecyclerView.Adapter<KategoriBukuAdapter.HolderData> {

    private List<BukuModel> Data;
    private Context ctx;
    private int layout;

    public KategoriBukuAdapter(Context ctx, List<BukuModel> getBuku, int newLayout) {
        this.ctx = ctx;
        this.Data = getBuku;
        this.layout = newLayout;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        KategoriBukuAdapter.HolderData holder = new KategoriBukuAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KategoriBukuAdapter.HolderData holder, int position) {
        BukuModel Model = Data.get(position);
        String imgURL = RetroServer.imgBukuURL;

        Picasso.get()
                .load(imgURL + Model.getNamaGambar())
                .into(holder.Gambar);

        holder.JudulBuku.setText(Model.getTitle());

        holder.Gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailBuku.class);
                intent.putExtra("ID_BUKU", Model.getID());
                ctx.startActivity(intent);
                ((Activity)ctx).overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView Gambar;
        TextView JudulBuku;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.gambar_buku);
            JudulBuku = itemView.findViewById(R.id.judul_buku);
        }
    }
}

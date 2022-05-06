package com.example.e_library.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.Kategori_buku.DetailBuku;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.Pinjam_buku.DetailPeminjaman;
import com.example.e_library.R;
import com.example.e_library.Response.TransactionsModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KodePinjamanAdapter extends RecyclerView.Adapter<KodePinjamanAdapter.HolderData> {

    private List<TransactionsModel> Data;
    private Context ctx;

    public KodePinjamanAdapter(Context ctx, List<TransactionsModel> getPinjamanSaya) {
        this.ctx = ctx;
        this.Data = getPinjamanSaya;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kode_buku, parent, false);
        KodePinjamanAdapter.HolderData holder = new KodePinjamanAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        TransactionsModel Model = Data.get(position);

        holder.card_kode_pinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailPeminjaman.class);
                intent.putExtra("id_pinjaman", Model.getId());
                ctx.startActivity(intent);
                ((Activity)ctx).overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
            }
        });

        holder.kode_transaksi.setText(Model.getKodePeminjaman());
        holder.tanggal_buku.setText(Model.getLoanDate());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        MaterialCardView card_kode_pinjaman;
        TextView kode_transaksi, tanggal_buku;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            card_kode_pinjaman = itemView.findViewById(R.id.card_kode_pinjaman);
            kode_transaksi = itemView.findViewById(R.id.kode_transaksi);
            tanggal_buku = itemView.findViewById(R.id.tanggal_pinjam);
        }
    }
}

package com.example.e_library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.TransactionsModel;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PinjamanSayaAdapter extends RecyclerView.Adapter<PinjamanSayaAdapter.HolderData> {

    private List<TransactionsModel> Data;
    private Context ctx;

    public PinjamanSayaAdapter(Context ctx, List<TransactionsModel> getPinjamanSaya) {
        this.ctx = ctx;
        this.Data = getPinjamanSaya;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pinjaman_saya, parent, false);
        PinjamanSayaAdapter.HolderData holder = new PinjamanSayaAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PinjamanSayaAdapter.HolderData holder, int position) {
        TransactionsModel Model = Data.get(position);
        String imgURL = RetroServer.imgBukuURL;

        holder.judul_buku.setText(Model.getBook().getTitle());
        holder.pengarang_buku.setText(Model.getBook().getAuthor());
        holder.tanggal_peminjaman.setText(Model.getLoanDate());
        holder.jadwal_peminjaman.setText(Model.getReturnDate());

        Picasso.get()
                .load(imgURL + Model.getImg())
                .into(holder.gambar_buku_detail_favorite);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ShapeableImageView gambar_buku_detail_favorite;
        TextView judul_buku, pengarang_buku, tanggal_peminjaman, jadwal_peminjaman;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            gambar_buku_detail_favorite = itemView.findViewById(R.id.gambar_buku_detail_favorite);
            judul_buku = itemView.findViewById(R.id.judul_buku);
            pengarang_buku = itemView.findViewById(R.id.pengarang_buku);
            tanggal_peminjaman = itemView.findViewById(R.id.tanggal_peminjaman);
            jadwal_peminjaman = itemView.findViewById(R.id.jadwal_peminjaman);
        }
    }
}

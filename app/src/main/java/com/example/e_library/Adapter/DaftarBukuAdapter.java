package com.example.e_library.Adapter;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.BluePrint.InputFilterMinMax;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.TransactionsModel;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DaftarBukuAdapter extends RecyclerView.Adapter<DaftarBukuAdapter.HolderData> {

    private List<TransactionsModel> Data;
    private Context ctx;

    public DaftarBukuAdapter(Context ctx, List<TransactionsModel> getDaftarBuku) {
        this.ctx = ctx;
        this.Data = getDaftarBuku;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_daftar_buku, parent, false);
        DaftarBukuAdapter.HolderData holder = new DaftarBukuAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        TransactionsModel Model = Data.get(position);
        String imgURL = RetroServer.imgBukuURL;

        holder.judul_buku.setText(Model.getBook().getTitle());
        holder.penerbit.setText(Model.getBook().getPublisher());

        holder.quantity_buku.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});

        holder.plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.quantity_buku.setText(Integer.parseInt(holder.quantity_buku.getText().toString()) + 1);
            }
        });

        holder.minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.quantity_buku.setText(Integer.parseInt(holder.quantity_buku.getText().toString()) - 1);
            }
        });

        Picasso.get()
                .load(imgURL + Model.getImg())
                .into(holder.gambar_daftar_buku);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ShapeableImageView gambar_daftar_buku;
        ImageView delete_buku, plus_icon, minus_icon;
        TextView judul_buku, penerbit;
        EditText quantity_buku;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            gambar_daftar_buku = itemView.findViewById(R.id.gambar_daftar_buku);
            judul_buku = itemView.findViewById(R.id.judul_buku);
            penerbit = itemView.findViewById(R.id.penerbit);
            delete_buku = itemView.findViewById(R.id.delete_buku);
            plus_icon = itemView.findViewById(R.id.plus_icon);
            minus_icon = itemView.findViewById(R.id.minus_icon);
            quantity_buku = itemView.findViewById(R.id.quantity_buku);
        }
    }
}

package com.example.e_library.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.Kategori_buku.DetailBuku;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.BukuModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;

public class BukuFavorite extends RecyclerView.Adapter<BukuFavorite.HolderData> {

    private List<BukuModel> Data;
    private Context ctx;

    public BukuFavorite(Context ctx, List<BukuModel> getBuku) {
        this.ctx = ctx;
        this.Data = getBuku;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.sering_di_pinjam, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BukuFavorite.HolderData holder, int position) {
//        BukuModel Model = Data.get(position);
//        String imgURL = RetroServer.imgBukuURL;
//
//        Picasso.get()
//                .load(imgURL + Model.getNamaGambar())
//                .into(holder.Gambar);
//
//        holder.Gambar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ctx, DetailBuku.class);
//                intent.putExtra("ID_BUKU", Model.getID());
//                ctx.startActivity(intent);
//                ((Activity)ctx).overridePendingTransition(R.anim.enter_rigth_to_left, R.anim.stay_position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        ImageView Gambar;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);
            Gambar = itemView.findViewById(R.id.gambar_buku_favorite);
        }
    }
}

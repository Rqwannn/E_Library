package com.example.e_library.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputFilter;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_library.BluePrint.InputFilterMinMax;
import com.example.e_library.Chart.DaftarBuku;
import com.example.e_library.Model.RetroServer;
import com.example.e_library.R;
import com.example.e_library.Response.BukuModel;
import com.example.e_library.Response.DetailCartModel;
import com.example.e_library.Response.TransactionsModel;
import com.example.e_library.RoomDB.AppDatabase;
import com.example.e_library.RoomDB.Product;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaftarBukuAdapter extends RecyclerView.Adapter<DaftarBukuAdapter.HolderData> {

    private List<DetailCartModel> Data;
    private Context ctx;
    int id_book[], quantityArray[];

    //setting onclicklistener
    private onNodeListener onNodeListener;

    public DaftarBukuAdapter(Context ctx, List<DetailCartModel> getDaftarBuku, onNodeListener onNodeListener) {
        this.ctx = ctx;
        this.Data = getDaftarBuku;
        this.onNodeListener = onNodeListener;
    }

    public int[] getIdBook() {
        return id_book;
    }


    public int[] getQuantity() {
        return quantityArray;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_daftar_buku, parent, false);
        DaftarBukuAdapter.HolderData holder = new DaftarBukuAdapter.HolderData(layout, onNodeListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        DetailCartModel Model = Data.get(position);
        String imgURL = RetroServer.imgBukuURL;

        holder.judul_buku.setText(Model.getBook().getTitle());
        holder.penerbit.setText(Model.getBook().getPublisher());

        holder.quantity_buku.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});
        holder.quantity_buku.setText("1");

        holder.quantity_buku.setTag(String.valueOf(Model.getBook().getID()));

        holder.plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = holder.quantity_buku.getText().toString().equals("10") ? "0" : holder.quantity_buku.getText().toString();
                holder.quantity_buku.setText(String.valueOf(Integer.parseInt(quantity) + 1));
            }
        });

//        AppDatabase db  = AppDatabase.getDbInstance(ctx);
//
//        Product product  = new Product();
//        product.id_book = Model.getBook().getID();
//        product.quantity = holder.quantity_buku.getText().toString();
//        db.productDao().insertProduct(product);

        holder.minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = holder.quantity_buku.getText().toString().equals("1") ? "2" : holder.quantity_buku.getText().toString();
                holder.quantity_buku.setText(String.valueOf(Integer.parseInt(quantity) - 1));
            }
        });

//        id_book[position] = Model.getBook().getID();
//        quantityArray[position] = Integer.parseInt(holder.quantity_buku.getText().toString());

        holder.delete_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Picasso.get()
                .load(imgURL + Model.getBook().getNamaGambar())
                .into(holder.gambar_daftar_buku);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder implements View.OnClickListener {
        ShapeableImageView gambar_daftar_buku;
        ImageView delete_buku, plus_icon, minus_icon;
        TextView judul_buku, penerbit;
        EditText quantity_buku;

        onNodeListener onNodeListener;


        public HolderData(@NonNull @NotNull View itemView, onNodeListener onNodeListener) {
            super(itemView);

            gambar_daftar_buku = itemView.findViewById(R.id.gambar_daftar_buku);
            judul_buku = itemView.findViewById(R.id.judul_buku);
            penerbit = itemView.findViewById(R.id.penerbit);
            delete_buku = itemView.findViewById(R.id.delete_buku);
            plus_icon = itemView.findViewById(R.id.plus_icon);
            minus_icon = itemView.findViewById(R.id.minus_icon);
            quantity_buku = itemView.findViewById(R.id.quantity_buku);

            this.onNodeListener = onNodeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNodeListener.onNodeClick(getAdapterPosition());
        }
    }

    public interface onNodeListener{
        void onNodeClick(int position);
    }
}

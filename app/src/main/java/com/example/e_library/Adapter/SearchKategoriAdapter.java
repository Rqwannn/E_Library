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
import com.example.e_library.Response.BookCategoriesModel;
import com.example.e_library.Response.PinjamanSayaModel;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchKategoriAdapter extends RecyclerView.Adapter<SearchKategoriAdapter.HolderData> {

    private List<BookCategoriesModel> Data;
    private Context ctx;

    public SearchKategoriAdapter(Context ctx, List<BookCategoriesModel> getCategories) {
        this.ctx = ctx;
        this.Data = getCategories;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_kategori, parent, false);
        SearchKategoriAdapter.HolderData holder = new SearchKategoriAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchKategoriAdapter.HolderData holder, int position) {
        BookCategoriesModel Model = Data.get(position);
        holder.text_kategori.setText(Model.getName());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView text_kategori;

        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            text_kategori = itemView.findViewById(R.id.text_kategori);

        }
    }
}

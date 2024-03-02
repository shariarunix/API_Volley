package com.shariarunix.apivolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    Context context;
    List<ProductModel> productList;

    public RecAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.layout_product_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
        ProductModel productObj = productList.get(position);

        Picasso.get()
                .load(productObj.getImages().get(0))
                .into(holder.imgThumbnail);
        holder.txtBrand.setText(productObj.getBrand());
        holder.txtCategory.setText(productObj.getCategory());
        holder.txtTitle.setText(productObj.getTitle());
        holder.txtPrice.setText(productObj.getPrice() + "$");
        holder.txtStock.setText(String.valueOf(productObj.getStock()));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardRoot;
        ImageView imgThumbnail;
        TextView txtBrand, txtCategory, txtTitle, txtPrice, txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardRoot = itemView.findViewById(R.id.cardRoot);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtStock = itemView.findViewById(R.id.txtStock);
        }
    }
}

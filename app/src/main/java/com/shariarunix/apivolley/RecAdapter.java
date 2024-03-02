package com.shariarunix.apivolley;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private final Context context;
    private final List<ProductModel> productList;

    public RecAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.layout_product_list_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
        ProductModel productObj = productList.get(position);

        Picasso.get()
                .load(productObj.getThumbnail())
                .into(holder.imgThumbnail);

        holder.txtBrand.setText(productObj.getBrand());
        holder.txtCategory.setText(productObj.getCategory());
        holder.txtTitle.setText(productObj.getTitle());
        holder.txtPrice.setText(productObj.getPrice() + "$");
        holder.txtStock.setText(String.valueOf(productObj.getStock()));

        holder.cardRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat aCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, holder.cardThumbParent, "imgTrans"
                );
                context.startActivity(new Intent(context, ProductActivity.class).putExtra(
                        "ProductModel", productObj
                ), aCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardRoot, cardThumbParent;
        ImageView imgThumbnail;
        TextView txtBrand, txtCategory, txtTitle, txtPrice, txtStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardRoot = itemView.findViewById(R.id.cardRoot);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            cardThumbParent = itemView.findViewById(R.id.cardThumbParent);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtStock = itemView.findViewById(R.id.txtStock);
        }
    }
}

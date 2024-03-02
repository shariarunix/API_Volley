package com.shariarunix.apivolley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    ViewPager vpImageSlider;
    TextView txtBrand, txtCategory, txtTitle, txtPrice, txtStock, txtDescription;
    AppCompatButton btnBuyNow;
    ImageView imgBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        imgBackBtn = findViewById(R.id.imgBackBtn);
        vpImageSlider = findViewById(R.id.vpImageSlider);
        txtBrand = findViewById(R.id.txtBrand);
        txtCategory = findViewById(R.id.txtCategory);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtStock = findViewById(R.id.txtStock);
        txtDescription = findViewById(R.id.txtDescription);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        ProductModel product = (ProductModel) getIntent().getSerializableExtra("ProductModel");

        assert product != null;
        txtBrand.setText(product.getBrand());
        txtCategory.setText(product.getCategory());
        txtTitle.setText(product.getTitle());
        txtPrice.setText(product.getPrice() + "$");
        txtStock.setText(String.valueOf(product.getStock()));
        txtDescription.setText(product.getDescription());

        List<String> images = product.getImages();

        vpImageSlider.setAdapter(new ImageSliderAdapter(ProductActivity.this, images));

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductActivity.this, "Order done", Toast.LENGTH_SHORT).show();
            }
        });

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
package com.shariarunix.apivolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ProductModel> productList;
    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        productList = new ArrayList<>();
        String url = "https://dummyjson.com/products";

        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(fetchProductsData(url));
    }

    private StringRequest fetchProductsData(String url) {
        return new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("products");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject productObj = jsonArray.getJSONObject(i);
                        JSONArray imgJsonArr = productObj.getJSONArray("images");
                        List<String> imagesList = new ArrayList<>();

                        for (int j = 0; j < imgJsonArr.length(); j++) {
                            imagesList.add(imgJsonArr.getString(j));
                        }

                        productList.add(
                                new ProductModel(
                                        productObj.getInt("id"),
                                        productObj.getString("title"),
                                        productObj.getString("description"),
                                        productObj.getInt("price"),
                                        productObj.getDouble("discountPercentage"),
                                        productObj.getDouble("rating"),
                                        productObj.getInt("stock"),
                                        productObj.getString("brand"),
                                        productObj.getString("category"),
                                        productObj.getString("thumbnail"),
                                        imagesList
                                )
                        );
                    }
                    recView.setAdapter(new RecAdapter(MainActivity.this, productList));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
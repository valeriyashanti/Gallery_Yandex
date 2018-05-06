package com.login.valeriya.galleryyandex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GalleryAdapter.OnItemClickListener{


    public static final String EXTRA_URL = "imageUrl";


    private RecyclerView mRecyclerView;
    private GalleryAdapter mGalleryAdapter;
    private ArrayList<GalleryItem> mGalleryList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        mGalleryList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    //парсинг и помещение значений в recyclerView через GalleryAdaptor
    private void parseJSON(){

        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=lifestyle&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i=0; i <jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String imageUrl = hit.getString("webformatURL");

                                mGalleryList.add(new GalleryItem(imageUrl));

                            }
                            mGalleryAdapter = new GalleryAdapter(MainActivity.this, mGalleryList);
                            mRecyclerView.setAdapter(mGalleryAdapter);
                            mGalleryAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    //опр. на какой элемент было произведено нажатие
    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent (this, DetailActivity.class);
        GalleryItem clickedItem = mGalleryList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        startActivity(detailIntent);

    }
    //Создаение меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //по выбранному элементу из меню - переходит в новое активити
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about){
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
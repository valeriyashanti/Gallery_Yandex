package com.login.valeriya.galleryyandex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import static com.login.valeriya.galleryyandex.MainActivity.EXTRA_URL;


// активити которое отображается при нажатии на элемент из нашего recyclerView
public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);

        ImageView  imageView = findViewById(R.id.image_view_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
    }
}

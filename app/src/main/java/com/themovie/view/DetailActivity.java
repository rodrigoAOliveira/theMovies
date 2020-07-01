package com.themovie.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.themovie.R;
import com.themovie.models.Results;

public class DetailActivity extends AppCompatActivity {

    private Results results;
    private ImageView ivMovie;
    private TextView tvTitle;
    private TextView tvOverview;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivMovie = findViewById(R.id.ivMovie);
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);

        Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w185" +getIntent().getSerializableExtra("image"))
                .centerCrop()
                .placeholder(R.drawable.ic_load)
                .into(ivMovie);

        tvTitle.setText(getIntent().getSerializableExtra("title").toString());
        tvOverview.setText(getIntent().getSerializableExtra("overview").toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

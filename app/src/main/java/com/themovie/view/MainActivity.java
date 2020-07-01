package com.themovie.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.themovie.EndlessRecyclerOnScrollListener;
import com.themovie.R;
import com.themovie.viewmodel.MoviesViewModel;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressMovies;
    private RecyclerView recyclerView;
    private int position = 1;
    private int totalPage = 0;
    private Adapter adapter;
    MoviesViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiViews();
        initRecycleView();
        model = new ViewModelProvider(this).get(MoviesViewModel.class);
        model.loadMovies(getString(R.string.api_key), getString(R.string.language), position);
        model.getMovies().observe(this, movies -> {
            totalPage = movies.getTotalPages();
            progressMovies.setVisibility(View.GONE);
            adapter.updateMovies(movies.getResults());
            adapter.notifyDataSetChanged();
        });
    }

    private void intiViews() {
        progressMovies = findViewById(R.id.progressMovies);
        recyclerView = findViewById(R.id.recyclerView);
    }

    void initRecycleView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        if (adapter == null) {
            adapter = new Adapter();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager, 3) {
            @Override
            public void onLoadMore() {
                position++;
                if (position <= totalPage) {
                    model.loadMovies(getString(R.string.api_key), getString(R.string.language), position);
                }
            }
        });

    }

}

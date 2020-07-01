package com.themovie.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.themovie.R;
import com.themovie.models.Results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Results> movies = new ArrayList<>();

    public void updateMovies(List<Results> movies) {
        if (movies == null) {
            this.movies = new ArrayList<>();
            this.movies.addAll(movies);
        } else {
            this.movies.addAll(movies);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide
                .with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + movies.get(position).getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.ic_load)
                .into(holder.ivMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), DetailActivity.class);
                i.putExtra("image",  movies.get(position).getPosterPath());
                i.putExtra("title",  movies.get(position).getTitle());
                i.putExtra("overview",  movies.get(position).getOverview());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.movies == null ? 0 : this.movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
        }
    }
}

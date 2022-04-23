package br.com.antoniojose.soccernews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.antoniojose.soccernews.databinding.ItemNewsBinding;
import br.com.antoniojose.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    ArrayList<News> list;

    public NewsAdapter(ArrayList<News> list){
            this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(layoutInflater, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = list.get( position );

        holder.binding.tvTitle.setText( news.getTitle());
        holder.binding.tvDescription.setText( news.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        private ItemNewsBinding binding;

        public NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}

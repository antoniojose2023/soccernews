package br.com.antoniojose.soccernews;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;

import br.com.antoniojose.soccernews.databinding.ItemNewsBinding;
import br.com.antoniojose.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    List<News> list;
    Context context;
    ClickListerner clickListener;

    public NewsAdapter(List<News> list, ClickListerner clickListener){
            this.list = list;
            this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding binding = ItemNewsBinding.inflate(layoutInflater, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        News news = list.get( position );

        holder.binding.tvTitle.setText( news.getTitle());
        holder.binding.tvDescription.setText( news.getDescription());
        Picasso.get().load(news.getImage()).into( holder.binding.ivNews );

        //abrir link de site
        holder.binding.buttonOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = news.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity( intent );

            }
        });

        //compartilhar link
        holder.binding.ivButtonShare.setOnClickListener(listerner -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, news.getLink());
            context.startActivity( Intent.createChooser(intent, "Escolha o app para compartilhar."));
        });


        holder.binding.ivButtonFavorite.setOnClickListener(listerner -> {
                news.setFavorito(!news.getFavorito());
               // holder.binding.ivButtonFavorite.setBackgroundResource(news.getFavorito() ? R.color.colorPrimary : R.color.purple_200);
               int cor = news.getFavorito() ? R.color.colorPrimary : R.color.colorBlack;
               holder.binding.ivButtonFavorite.setColorFilter(context.getResources().getColor(cor));

                clickListener.click( news );
                notifyItemChanged(position);
        });

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

    public interface ClickListerner{
            void click(News news);
    }
}

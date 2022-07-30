package me.dio.soccernews.ui.adapters;

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

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.NewsItemBinding;
import me.dio.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image).into(holder.binding.ivThumbnail);

        // implementação da funcionalidade abrir link
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            context.startActivity(i);
        });

        // implementação da funcionalidade de compartilhar
        holder.binding.ivShare.setOnClickListener(onClick -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, news.link);
            sendIntent.setType("text/plain");
            context.startActivity(Intent.createChooser(sendIntent, "Share"));
        });

        // implementação da funcionalidade de Favoritar -> o evento sera instanciado pelo fragment
        holder.binding.ivFavorite.setOnClickListener(onClick -> {
            // se já estiver favorito ele vai desfavoritar, e vice e versa
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });
        int favoriteColor = news.favorite ? R.color.favorite : R.color.black;
        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;
        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}

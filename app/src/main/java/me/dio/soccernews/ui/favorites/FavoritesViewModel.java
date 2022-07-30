package me.dio.soccernews.ui.favorites;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.dio.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<News> mNews;

    public FavoritesViewModel() {
        mNews = new MutableLiveData<>();
        // mNews.setValue("This is dashboard fragment");
    }
}
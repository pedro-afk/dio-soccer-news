package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        // TODO remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Teste 1, legal!", "Esse é o teste de numero 1, legal!"));
        news.add(new News("Teste 2, legal!", "Esse é o teste de numero 2, legal!"));
        news.add(new News("Teste 3, legal!", "Esse é o teste de numero 3, legal!"));
        news.add(new News("Teste 4, legal!", "Esse é o teste de numero 4, legal!"));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}
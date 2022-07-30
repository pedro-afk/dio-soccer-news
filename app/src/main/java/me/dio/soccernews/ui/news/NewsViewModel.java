package me.dio.soccernews.ui.news;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.data.local.AppDatabase;
import me.dio.soccernews.data.remote.SoccerNewsApi;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;

    public NewsViewModel() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedro-afk.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         api = retrofit.create(SoccerNewsApi.class);
         findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    // TODO pensar em uma estrategia de tratamento de erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                // TODO pensar em uma estrategia de tratamento de erros
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}
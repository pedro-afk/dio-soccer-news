package me.dio.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.soccernews.domain.News;

@Dao
public interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    LiveData<Long> save(News news);

    @Query("SELECT * FROM News WHERE favorite = 1")
    LiveData<List<News>> loadFavoriteNews();
}

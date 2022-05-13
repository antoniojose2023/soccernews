package br.com.antoniojose.soccernews.data.datalocal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.antoniojose.soccernews.domain.News;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Query("select * from tb_news where favorito = 1")
    List<News> getListNews();
}

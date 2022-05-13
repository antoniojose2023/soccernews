package br.com.antoniojose.soccernews.data.datalocal;

import androidx.room.RoomDatabase;

import br.com.antoniojose.soccernews.domain.News;

@androidx.room.Database(entities = {News.class}, version = 1)
public abstract class Database extends RoomDatabase {
      public abstract NewsDao getDao();
}

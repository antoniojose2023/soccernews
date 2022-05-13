package br.com.antoniojose.soccernews.data;

import android.app.Activity;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.antoniojose.soccernews.App;
import br.com.antoniojose.soccernews.data.dataRemote.SoccerNewsApi;
import br.com.antoniojose.soccernews.data.datalocal.Database;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerRepository {

    private static final String remote_url_base = "https://digitalinnovationone.github.io/soccer-news-api/";

    private static SoccerNewsApi remoteApi;
    private static Database dataLocal;

    public SoccerNewsApi getRemoteApi(){
        return remoteApi;
    }

    public Database getDataLocal(){
        return dataLocal;
    }

    public SoccerRepository(){
        remoteApi = new Retrofit.Builder()
                .baseUrl(remote_url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);


       dataLocal = Room.databaseBuilder(App.getIntance(), Database.class, "news_db").allowMainThreadQueries().build();
    }

    public SoccerRepository getIntance(){
            return LazyHolder.instance;
    }

    private static class LazyHolder{
        private static final SoccerRepository instance = new SoccerRepository();
    }

}

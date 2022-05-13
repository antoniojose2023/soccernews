package br.com.antoniojose.soccernews.data.dataRemote;

import java.util.List;

import br.com.antoniojose.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface SoccerNewsApi {

    @GET("news.json")
    Call<List<News>> getNews();

}

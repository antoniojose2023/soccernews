package br.com.antoniojose.soccernews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import br.com.antoniojose.soccernews.data.SoccerRepository;
import br.com.antoniojose.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> states = new MutableLiveData<>();

    public NewsViewModel() {
        findNews();


    }

    private void findNews(){
        states.setValue( State.DOING );

        new SoccerRepository().getIntance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                    if(response.isSuccessful()){
                        news.setValue( response.body() );
                        states.setValue( State.DONE );
                    }else{
                        states.setValue( State.ERRO );
                    }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                states.setValue( State.ERRO );
            }
        });

    }

    public void save(News news){
       new SoccerRepository().getDataLocal().getDao().insert(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

    public LiveData<State> getStates(){
        return states;
    }


    public enum State{
        DOING, ERRO, DONE

    }
}
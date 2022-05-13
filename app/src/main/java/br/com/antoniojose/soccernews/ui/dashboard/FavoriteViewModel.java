package br.com.antoniojose.soccernews.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.antoniojose.soccernews.data.SoccerRepository;
import br.com.antoniojose.soccernews.domain.News;
import br.com.antoniojose.soccernews.ui.home.NewsViewModel;

public class FavoriteViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<States> states = new MutableLiveData<>();

    public FavoriteViewModel() {
        findNews();
    }

    private void findNews(){
        states.setValue( States.DOING );

        try{
            List<News> list =  new SoccerRepository().getIntance().getDataLocal().getDao().getListNews();
            news.setValue( list );
            states.setValue( States.DONE );
        }catch (Exception e){
            states.setValue( States.ERRO );
            Log.i("www", "findNews: "+e.getMessage());
        }

    }

    public LiveData<List<News>> getNewsDataLocal(){
        return news;
    }

    public LiveData<States> getStatesDataLocal(){
        return states;
    }

    public enum States{
        DOING, ERRO, DONE
    }

}
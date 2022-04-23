package br.com.antoniojose.soccernews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.antoniojose.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        ArrayList list = new ArrayList<News>();
        list.add(new News("aaa", "aaa aaaaa a aaaaaaaa"));
        list.add(new News("bbb", "aaa aaaaa a aaaaaaaa"));
        list.add(new News("ccc", "aaa aaaaa a aaaaaaaa"));

        this.news.setValue( list );
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}
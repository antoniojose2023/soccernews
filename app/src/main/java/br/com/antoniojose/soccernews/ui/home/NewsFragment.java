package br.com.antoniojose.soccernews.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.antoniojose.soccernews.App;
import br.com.antoniojose.soccernews.MainActivity;
import br.com.antoniojose.soccernews.NewsAdapter;
import br.com.antoniojose.soccernews.R;
import br.com.antoniojose.soccernews.data.SoccerRepository;
import br.com.antoniojose.soccernews.databinding.FragmentHomeBinding;
import br.com.antoniojose.soccernews.domain.News;
import br.com.antoniojose.soccernews.util.RefreshUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements NewsAdapter.ClickListerner {

    private FragmentHomeBinding binding;
    private NewsViewModel newsViewModel;

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =  new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(requireActivity()));

       observers();
       observersStates();

       binding.swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
                 observers();
                 observersStates();
           }
       });

        return root;
    }

    private void observers(){
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, this));
            Log.i("www", "observers: "+news.size());
        });
    }

    private void observersStates(){
        newsViewModel.getStates().observe(getViewLifecycleOwner(), state ->  {
           switch (state){
               case DOING: binding.swRefresh.setRefreshing(true);
                   break;
               case ERRO: binding.swRefresh.setRefreshing(false);
                   break;
               case DONE: binding.swRefresh.setRefreshing(false);
                   break;
           }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void click(News news) {
           newsViewModel.save(news);
          // refreshActivity();
          RefreshUI.refreshAcitvity();
    }


}
package br.com.antoniojose.soccernews.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import br.com.antoniojose.soccernews.App;
import br.com.antoniojose.soccernews.NewsAdapter;
import br.com.antoniojose.soccernews.databinding.FragmentDashboardBinding;
import br.com.antoniojose.soccernews.domain.News;
import br.com.antoniojose.soccernews.ui.home.NewsViewModel;
import br.com.antoniojose.soccernews.util.RefreshUI;

public class FavoriteFragment extends Fragment implements NewsAdapter.ClickListerner {


    private FragmentDashboardBinding binding;
    FavoriteViewModel favoriteViewModel ;
    NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =  new ViewModelProvider(this).get(FavoriteViewModel.class);
        newsViewModel =  new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

    public void observers(){
        binding.rvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        favoriteViewModel.getNewsDataLocal().observe(getViewLifecycleOwner(), news -> {
            NewsAdapter adapter = new NewsAdapter(news, this);
            binding.rvNews.setAdapter(adapter);
        });
    }

    public void observersStates(){
       favoriteViewModel.getStatesDataLocal().observe(getViewLifecycleOwner(), states ->{
           switch (states){
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

    @Override
    public void click(News news) {
          newsViewModel.save(news);
          RefreshUI.refreshAcitvity();
    }
}
package br.com.antoniojose.soccernews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import br.com.antoniojose.soccernews.NewsAdapter;
import br.com.antoniojose.soccernews.databinding.FragmentHomeBinding;
import br.com.antoniojose.soccernews.domain.News;

public class NewsFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =  new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(requireActivity()));

        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
                binding.rvNews.setAdapter(new NewsAdapter((ArrayList<News>) news));
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
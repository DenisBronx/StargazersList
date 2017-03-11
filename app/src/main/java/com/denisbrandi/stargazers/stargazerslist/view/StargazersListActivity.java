package com.denisbrandi.stargazers.stargazerslist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denisbrandi.stargazers.R;
import com.denisbrandi.stargazers.StargazersApp;
import com.denisbrandi.stargazers.dagger.component.DaggerStargazersListComponent;
import com.denisbrandi.stargazers.dagger.module.StargazersModule;
import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;

import java.util.List;

import javax.inject.Inject;

public class StargazersListActivity extends AppCompatActivity implements StargazersListViewModel.StargazersListViewModelListener {

    @Inject
    StargazersListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stargazerslist);

        DaggerStargazersListComponent.builder()
                .netComponent(((StargazersApp) getApplication()).getNetComponent())
                .stargazersModule(new StargazersModule(this))
                .build().inject(this);


    }

    @Override
    public void onNewData(List<Stargazer> stargazers) {

    }

    @Override
    public void onDataCleared() {

    }
}

package com.denisbrandi.stargazers.stargazerslist.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denisbrandi.stargazers.R;
import com.denisbrandi.stargazers.StargazersApp;
import com.denisbrandi.stargazers.base.BaseActivity;
import com.denisbrandi.stargazers.dagger.component.DaggerStargazersListComponent;
import com.denisbrandi.stargazers.dagger.module.StargazersModule;
import com.denisbrandi.stargazers.databinding.ActivityStargazerslistBinding;
import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;

import java.util.List;

import javax.inject.Inject;

public class StargazersListActivity extends BaseActivity implements StargazersListViewModel.StargazersListViewModelListener, Paginator.PaginatorListener {

    @Inject
    StargazersListViewModel viewModel;

    private ActivityStargazerslistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stargazerslist);

        DaggerStargazersListComponent.builder()
                .netComponent(((StargazersApp) getApplication()).getNetComponent())
                .stargazersModule(new StargazersModule(this, this))
                .build().inject(this);

        binding.setViewModel(viewModel);


    }

    @Override
    public void onNewData(List<Stargazer> stargazers) {

    }

    @Override
    public void onDataCleared() {

    }

    @Override
    public void onPaginationProgress() {
        viewModel.getStargazers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.cleanup();
    }
}

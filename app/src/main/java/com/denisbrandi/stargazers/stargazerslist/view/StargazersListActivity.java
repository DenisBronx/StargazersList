package com.denisbrandi.stargazers.stargazerslist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.denisbrandi.stargazers.R;
import com.denisbrandi.stargazers.StargazersApp;
import com.denisbrandi.stargazers.base.BaseActivity;
import com.denisbrandi.stargazers.dagger.component.DaggerStargazersListComponent;
import com.denisbrandi.stargazers.dagger.module.StargazersModule;
import com.denisbrandi.stargazers.databinding.ActivityStargazerslistBinding;
import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.navigation.Navigator;
import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.rx.RxFullRecyclerViewAdapter;
import com.denisbrandi.stargazers.stargazerslist.adapter.StargazersListAdapter;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.ItemListStargazersViewModel;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

public class StargazersListActivity extends BaseActivity implements StargazersListViewModel.StargazersListViewModelListener, Paginator.PaginatorListener {

    @Inject
    StargazersListViewModel viewModel;

    @Inject
    StargazersListAdapter adapter;

    @Inject
    Navigator navigator;

    private ActivityStargazerslistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stargazerslist);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.list.setLayoutManager(layoutManager);

        DaggerStargazersListComponent.builder()
                .netComponent(((StargazersApp) getApplication()).getNetComponent())
                .stargazersModule(new StargazersModule(this, this, this))
                .build().inject(this);

        binding.setViewModel(viewModel);

        binding.list.setAdapter(adapter);

        compositeSubscription.add(RxRecyclerView.scrollEvents(binding.list).subscribe(new Action1<RecyclerViewScrollEvent>() {
            @Override
            public void call(RecyclerViewScrollEvent recyclerViewScrollEvent) {

                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = binding.list.getChildCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                boolean progressVisible = adapter.getItemCount() > 0 && adapter.getItem(adapter.getItemCount() - 1).isProgress();

                viewModel.getPaginator().calculatePagination(totalItemCount, visibleItemCount, firstVisibleItem, progressVisible);
            }
        }));

        compositeSubscription.add(RxFullRecyclerViewAdapter.dataChanges(adapter).subscribe(new Action1<StargazersListAdapter>() {
            @Override
            public void call(StargazersListAdapter stargazersListAdapter) {
                viewModel.setDataCount(adapter.getItemCount());
            }
        }));


    }

    @Override
    public void onNewData(List<Stargazer> stargazers) {
        if (stargazers != null)
            for (Stargazer stargazer : stargazers) {
                adapter.addItem(new ItemListStargazersViewModel(stargazer, navigator));
            }
    }

    @Override
    public void onDataCleared() {
        adapter.clear();
    }

    @Override
    public void onPaginationProgress() {
        viewModel.getStargazers();
        adapter.addItem(new ItemListStargazersViewModel(true));
    }

    @Override
    public void onLimitReached() {
        adapter.removeProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.cleanup();
    }
}

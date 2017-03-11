package com.denisbrandi.stargazers.dagger.module;

import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 11/03/17.
 */
@Module
public class StargazersModule {

    private StargazersListViewModel.StargazersListViewModelListener viewModelListener;
    private Paginator.PaginatorListener paginatorListener;

    public StargazersModule(StargazersListViewModel.StargazersListViewModelListener viewModelListener, Paginator.PaginatorListener paginatorListener) {
        this.viewModelListener = viewModelListener;
        this.paginatorListener = paginatorListener;
    }

    @Provides
    Paginator providePaginator() {
        return new Paginator(paginatorListener);
    }

    @Provides
    StargazersListViewModel provideStargazersListViewModel(StargazersApi stargazersApi, Paginator paginator) {
        return new StargazersListViewModel(stargazersApi, paginator, viewModelListener);
    }

}

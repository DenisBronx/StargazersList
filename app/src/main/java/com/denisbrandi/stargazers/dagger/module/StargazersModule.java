package com.denisbrandi.stargazers.dagger.module;

import com.denisbrandi.stargazers.base.BaseActivity;
import com.denisbrandi.stargazers.navigation.Navigator;
import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.stargazerslist.adapter.StargazersListAdapter;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by denis on 11/03/17.
 */
@Module
public class StargazersModule {

    private BaseActivity baseActivity;
    private StargazersListViewModel.StargazersListViewModelListener viewModelListener;
    private Paginator.PaginatorListener paginatorListener;

    public StargazersModule(BaseActivity baseActivity,StargazersListViewModel.StargazersListViewModelListener viewModelListener, Paginator.PaginatorListener paginatorListener) {
        this.baseActivity = baseActivity;
        this.viewModelListener = viewModelListener;
        this.paginatorListener = paginatorListener;
    }

    @Provides
    StargazersListAdapter provideStargazersListAdapter() {
        return new StargazersListAdapter();
    }

    @Provides
    Paginator providePaginator() {
        return new Paginator(paginatorListener);
    }

    @Provides
    StargazersListViewModel provideStargazersListViewModel(StargazersApi stargazersApi, Paginator paginator) {
        return new StargazersListViewModel(stargazersApi, paginator, Schedulers.newThread(), AndroidSchedulers.mainThread(), viewModelListener);
    }

    @Provides
    Navigator provideNavigator() {
        return new Navigator(baseActivity);
    }

}

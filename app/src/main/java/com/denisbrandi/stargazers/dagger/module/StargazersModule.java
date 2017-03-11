package com.denisbrandi.stargazers.dagger.module;

import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazersListViewModel;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 11/03/17.
 */
@Module
public class StargazersModule {

    private StargazersListViewModel.StargazersListViewModelListener listener;

    public StargazersModule(StargazersListViewModel.StargazersListViewModelListener listener) {
        this.listener = listener;
    }

    @Provides
    StargazersListViewModel provideStargazersListViewModel(StargazersApi stargazersApi) {
        return new StargazersListViewModel(stargazersApi, listener);
    }

}

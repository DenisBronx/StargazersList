package com.denisbrandi.stargazers.dagger.module;

import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazerDetailViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 12/03/17.
 */
@Module
public class StargazerDetailModule {

    private Stargazer stargazer;

    public StargazerDetailModule(Stargazer stargazer) {
        this.stargazer = stargazer;
    }

    @Provides
    StargazerDetailViewModel provideStargazerDetailViewModel() {
        return new StargazerDetailViewModel(stargazer);
    }

}

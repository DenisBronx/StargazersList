package com.denisbrandi.stargazers.dagger.component;

import com.denisbrandi.stargazers.dagger.module.StargazerDetailModule;
import com.denisbrandi.stargazers.dagger.scope.ActivityScope;
import com.denisbrandi.stargazers.stargazerslist.view.StargazerDetailActivity;

import dagger.Component;

/**
 * Created by denis on 12/03/17.
 */
@ActivityScope
@Component(modules = StargazerDetailModule.class, dependencies = NetComponent.class)
public interface StargazerDetailComponent {

    void inject(StargazerDetailActivity activity);

}

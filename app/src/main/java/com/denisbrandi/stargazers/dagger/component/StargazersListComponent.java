package com.denisbrandi.stargazers.dagger.component;

import com.denisbrandi.stargazers.dagger.module.StargazersModule;
import com.denisbrandi.stargazers.dagger.scope.ActivityScope;
import com.denisbrandi.stargazers.stargazerslist.view.StargazersListActivity;

import dagger.Component;

/**
 * Created by denis on 11/03/17.
 */
@ActivityScope
@Component(modules = StargazersModule.class, dependencies = NetComponent.class)
public interface StargazersListComponent {
    void inject(StargazersListActivity activity);
}

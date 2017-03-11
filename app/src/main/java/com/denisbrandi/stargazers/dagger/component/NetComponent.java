package com.denisbrandi.stargazers.dagger.component;

import com.denisbrandi.stargazers.MainActivity;
import com.denisbrandi.stargazers.dagger.module.AppModule;
import com.denisbrandi.stargazers.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by denis on 11/03/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}

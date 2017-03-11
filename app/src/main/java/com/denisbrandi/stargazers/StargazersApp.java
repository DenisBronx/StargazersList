package com.denisbrandi.stargazers;

import android.app.Application;

import com.denisbrandi.stargazers.dagger.component.DaggerNetComponent;
import com.denisbrandi.stargazers.dagger.component.NetComponent;
import com.denisbrandi.stargazers.dagger.module.AppModule;
import com.denisbrandi.stargazers.dagger.module.NetModule;

/**
 * Created by denis on 11/03/17.
 */

public class StargazersApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}

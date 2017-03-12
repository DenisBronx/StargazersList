package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.navigation.Navigator;

/**
 * Created by denis on 11/03/17.
 */

public class ItemListStargazersViewModel {

    private boolean progress;
    private Stargazer stargazer;
    private Navigator navigator;

    public ItemListStargazersViewModel(boolean progress) {
        this.progress = progress;
    }

    public ItemListStargazersViewModel(Stargazer stargazer, Navigator navigator) {
        this.stargazer = stargazer;
        this.navigator = navigator;
    }

    public void goToDetail() {
        navigator.goToStargazerDetail(stargazer);
    }

    public String getName() {
        return stargazer.getLogin();
    }

    public String getImageUrl() {
        return stargazer.getAvatarUrl();
    }

    public boolean isProgress() {
        return progress;
    }
}

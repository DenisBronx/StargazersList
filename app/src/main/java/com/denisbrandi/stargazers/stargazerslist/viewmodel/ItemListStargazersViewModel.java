package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import com.denisbrandi.stargazers.model.Stargazer;

/**
 * Created by denis on 11/03/17.
 */

public class ItemListStargazersViewModel {

    private boolean progress;
    private Stargazer stargazer;

    public ItemListStargazersViewModel(boolean progress) {
        this.progress = progress;
    }

    public ItemListStargazersViewModel(Stargazer stargazer) {
        this.stargazer = stargazer;
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

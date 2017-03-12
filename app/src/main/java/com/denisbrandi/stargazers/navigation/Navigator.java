package com.denisbrandi.stargazers.navigation;

import android.content.Intent;

import com.denisbrandi.stargazers.base.BaseActivity;
import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.stargazerslist.view.StargazerDetailActivity;

import org.parceler.Parcels;

/**
 * Created by denis on 12/03/17.
 */

public class Navigator {

    private BaseActivity baseActivity;

    public Navigator(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void goToStargazerDetail(Stargazer stargazer) {
        Intent intent = new Intent(baseActivity, StargazerDetailActivity.class);
        intent.putExtra(StargazerDetailActivity.STARGAZER, Parcels.wrap(stargazer));
        baseActivity.startActivity(intent);
    }

}

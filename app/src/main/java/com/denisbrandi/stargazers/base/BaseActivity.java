package com.denisbrandi.stargazers.base;

import android.support.v7.app.AppCompatActivity;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by denis on 11/03/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}

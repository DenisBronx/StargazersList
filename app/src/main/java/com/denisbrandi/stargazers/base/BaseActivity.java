package com.denisbrandi.stargazers.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by denis on 11/03/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    protected void setBackButton(Toolbar toolbar) {
        setToolbar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

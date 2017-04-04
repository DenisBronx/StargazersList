package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.utils.StringUtils;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by denis on 11/03/17.
 */

public class StargazersListViewModel {

    public interface StargazersListViewModelListener {
        void onNewData(List<Stargazer> stargazers);

        void onDataCleared();
    }

    private StargazersApi stargazersApi;
    private StargazersListViewModelListener listener;
    private Subscription apiSubscription;
    private Paginator paginator;

    private Scheduler backgroundScheduler, mainScheduler;

    public ObservableField<String> owner = new ObservableField<>();
    public ObservableField<String> repository = new ObservableField<>();
    public ObservableBoolean showProgress = new ObservableBoolean(false);
    public ObservableBoolean showEmptyView = new ObservableBoolean(false);
    public ObservableBoolean showPlaceholder = new ObservableBoolean(true);

    @Inject
    public StargazersListViewModel(StargazersApi stargazersApi, Paginator paginator, Scheduler backgroundScheduler, Scheduler mainScheduler, StargazersListViewModelListener listener) {
        this.stargazersApi = stargazersApi;
        this.paginator = paginator;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
        this.listener = listener;
    }

    public void startSearch() {
        paginator.cleanup();
        showProgress.set(true);
        listener.onDataCleared();
        unsubscribe();
        getStargazers();
    }

    public void setDataCount(int dataCount) {
        showEmptyView.set(dataCount == 0);
    }

    public void calculatePagination(int totalItemCount, int visibleItemCount, int firstVisibleItem, boolean isPaginationProgressVisible) {
        paginator.calculatePagination(totalItemCount, visibleItemCount, firstVisibleItem, isPaginationProgressVisible);
    }

    public void getStargazers() {

        if (StringUtils.isEmpty(owner.get()) || StringUtils.isEmpty(repository.get())) {
            showProgress.set(false);
            showPlaceholder.set(true);
            listener.onDataCleared();
            return;
        }

        apiSubscription = stargazersApi
                .getStargazers(owner.get(), repository.get(), paginator.getPage())
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<List<Stargazer>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showNewData(null);
                    }

                    @Override
                    public void onNext(List<Stargazer> stargazers) {
                        showNewData(stargazers);
                    }
                });
    }

    void showNewData(List<Stargazer> stargazers) {
        showProgress.set(false);
        showPlaceholder.set(false);
        listener.onNewData(stargazers);
        paginator.setLimitReached(stargazers == null || stargazers.size() == 0);
    }

    public void cleanup() {
        unsubscribe();
    }

    private void unsubscribe() {
        if (apiSubscription != null && !apiSubscription.isUnsubscribed())
            apiSubscription.unsubscribe();
    }


}

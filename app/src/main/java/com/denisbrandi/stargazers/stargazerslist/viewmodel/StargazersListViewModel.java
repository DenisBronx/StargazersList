package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

/**
 * Created by denis on 11/03/17.
 */

public class StargazersListViewModel {

    public interface StargazersListViewModelListener {
        void onNewData(List<Stargazer> stargazers);
        void onDataCleared();
    }

    private int page = 1;

    private StargazersApi stargazersApi;
    private StargazersListViewModelListener listener;
    private Subscription apiSubscription;

    @Inject
    public StargazersListViewModel(StargazersApi stargazersApi, StargazersListViewModelListener listener) {
        this.stargazersApi = stargazersApi;
        this.listener = listener;
    }

    public void updateList(String owner, String repo) {
        unsubscribe();
        page = 1;
        getStargazers(owner, repo);
    }

    private void getStargazers(String owner, String repo) {
        apiSubscription = stargazersApi.getStargazers(owner, repo, page).subscribe(new Observer<List<Stargazer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Stargazer> stargazers) {
                listener.onNewData(stargazers);
            }
        });
    }

    public void cleanup() {
        page = 1;
        unsubscribe();
    }

    private void unsubscribe() {
        if (apiSubscription != null && !apiSubscription.isUnsubscribed())
            apiSubscription.unsubscribe();
    }


}
